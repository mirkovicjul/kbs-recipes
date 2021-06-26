package services

import com.typesafe.scalalogging.LazyLogging
import database.{IngredientRepo, MeasurementRepo, RecipeRepo, UserRepo}
import drools.SessionCache
import drools.cep.WantsNewRecommendation
import drools.conclusion.Vegetarian
import drools.recommendation.{Recommendation, StorageItem, User, Ingredient => IngredientFact, Measurement => MeasurementFact, Recipe => RecipeFact, RecipeIngredient => RecipeIngredientFact}
import org.kie.api.runtime.KieSession

import javax.inject.Inject
import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.jdk.CollectionConverters.IteratorHasAsScala
import java.util.{ArrayList => JArrayList}

trait RecommendationService {

  def initSession(userId: Long): Unit

  def recommendOnHomepage(userId: Long): Seq[Recommendation]

  def somethingNew(userId: Long): Seq[Recommendation]

}

class RecommendationServiceImpl @Inject()(
    sessions: SessionCache,
    userRepo: UserRepo,
    recipeRepo: RecipeRepo,
    ingredientRepo: IngredientRepo,
    measurementRepo: MeasurementRepo
) extends RecommendationService
    with LazyLogging {

  override def initSession(userId: Long): Unit = {
    logger.info(s"Initializing KIE Session for user $userId...")

    val session: KieSession = sessions.simpleSession(userId)

    logger.info(s"Adding user $userId to KIE Session...")
    session.insert(
      new User(
        userId,
        new JArrayList[IngredientFact](), // TODO: Replace with real values
        new JArrayList[IngredientFact](),
        new JArrayList[IngredientFact](),
        new JArrayList[IngredientFact](),
        new JArrayList[StorageItem]()
      )
    )

    logger.info(s"Adding ingredients to user $userId KIE Session...")
    val allIngredients: Seq[IngredientFact] =
      ingredientRepo
        .allIngredients()
        .map { i =>
          new IngredientFact(
            i.id,
            i.name,
            i.`type`,
            i.fats,
            i.carbs,
            i.protein
          )
        }
    allIngredients.foreach(session.insert)

    logger.info(s"Adding measurements to user $userId KIE Session...")
    val allMeasurements: Seq[MeasurementFact] =
      measurementRepo
        .allMeasurements()
        .map(m => new MeasurementFact(m.id, m.measurement, m.proportion))
    allMeasurements.foreach(session.insert)

    logger.info(s"Adding recipes to user $userId KIE Session...")
    recipeRepo
      .allRecipes()
      .foreach { recipe =>
        val recipeIngredients: Seq[RecipeIngredientFact] =
          ingredientRepo
            .recipeIngredients(recipe.getId)
            .map { ri =>
              new RecipeIngredientFact(
                allIngredients.filter(_.getId == ri.ingredientId).head,
                ri.amount,
                allMeasurements.filter(_.getId == ri.measurementId).head
              )
            }

        val recipeFact = new RecipeFact(
          recipe.getId,
          recipe.getTitle,
          recipeIngredients.asJava,
          recipe.getVegan,
          recipe.getVegetarian,
          recipe.getJunkFood,
          recipe.getNumberOfPortions.toInt
        )
        session.insert(recipeFact)
      }

    logger.info(s"KIE Session initialized for user $userId.")
  }

  override def recommendOnHomepage(userId: Long): Seq[Recommendation] = {
    val session: KieSession = sessions.simpleSession(userId)

    session.getAgenda().getAgendaGroup("Conclusion").setFocus()
    session.getAgenda().getAgendaGroup("Recommendation").setFocus()

    session.fireAllRules

    session
      .getQueryResults("SimpleResults")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq
  }

  override def somethingNew(userId: Long): Seq[Recommendation] = {
    def wantsNewRecommendation(implicit session: KieSession): Unit = {
      session
        .getEntryPoint("recommendation frequency")
        .insert(new WantsNewRecommendation())
    }

    implicit val session: KieSession = sessions.simpleSession(userId)

    wantsNewRecommendation

    session.getAgenda().getAgendaGroup("Recommendation").setFocus()
    session.fireAllRules

    session
      .getQueryResults("SimpleResults")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq
  }

}
