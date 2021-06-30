package services

import com.typesafe.scalalogging.LazyLogging
import database._
import drools.SessionCache
import drools.cep.WantsNewRecommendation
import drools.recommendation.{MadeRecipe, Recommendation, StartedEngine, StorageItem, User, Ingredient => IngredientFact, Measurement => MeasurementFact, Recipe => RecipeFact, RecipeIngredient => RecipeIngredientFact}
import org.kie.api.runtime.KieSession
import org.kie.api.runtime.rule.EntryPoint

import java.util
import java.util.{ArrayList => JArrayList}
import javax.inject.Inject
import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.jdk.CollectionConverters.{IterableHasAsScala, IteratorHasAsScala}

trait RecommendationService {

  def initSession(userId: Long): Unit

  def removeSession(userId: Long): Unit

  def recommendOnHomepage(userId: Long): Option[Recommendation]

  def somethingNew(userId: Long): Seq[Recommendation]

}

class RecommendationServiceImpl @Inject()(
                                           sessions: SessionCache,
                                           userRepo: UserRepo,
                                           recipeRepo: RecipeRepo,
                                           ingredientRepo: IngredientRepo,
                                           ingredientStorageRepo: IngredientStorageRepo,
                                           historyRepo: HistoryRepo,
                                           measurementRepo: MeasurementRepo
                                         ) extends RecommendationService
  with LazyLogging {

  override def initSession(userId: Long): Unit = {
    logger.info(s"Initializing KIE Session for user $userId...")

    if (sessions.exist(userId)) {
      logger.info(s"Session for user $userId already initialized.")
    } else {

      val session: KieSession = sessions.simpleSession(userId)

      logger.info(s"Adding ingredients to user $userId KIE Session...")
      val allIngredients: Seq[IngredientFact] =
        ingredientRepo
          .allIngredientsScala()
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
      val allRecipes: Seq[RecipeFact] = recipeRepo
        .allRecipes()
        .map { recipe =>
          val recipeIngredients: Seq[RecipeIngredientFact] =
            ingredientRepo
              .recipeIngredientsScala(recipe.getId)
              .map { ri =>
                new RecipeIngredientFact(
                  allIngredients.filter(_.getId == ri.ingredientId).head,
                  ri.amount,
                  allMeasurements.filter(_.getId == ri.measurementId).head
                )
              }

          new RecipeFact(
            recipe.getId,
            recipe.getTitle,
            recipeIngredients.asJava,
            recipe.getVegan,
            recipe.getVegetarian,
            recipe.getJunkFood,
            recipe.getNumberOfPortions.toInt
          )

        }

      allRecipes.foreach(r => session.insert(r))

      logger.info(s"Adding user $userId to KIE Session...")
      val user = userRepo.oneBlocking(userId).get
      user.getLikes.forEach(i => logger.info(s"User $userId likes " + i.getIngredient))

      val storage = ingredientStorageRepo.getIngredientStorage(userId).map(is => new StorageItem(allIngredients.find(_.getId == is.getIngredient.getId).get, is.getQuantity,
        allMeasurements.find(_.getId == is.getMeasurement.getId).get, is.getBestBefore)).asJava


      session.insert(
        new User(
          userId,
          allIngredients.filter(r => user.getLikes.asScala.map(_.getId).toSeq.contains(r.getId)).asJava, // TODO: test with manually created facts
          allIngredients.filter(r => user.getAllergies.asScala.map(_.getId).toSeq.contains(r.getId)).asJava,
          allIngredients.filter(r => user.getDislikes.asScala.map(_.getId).toSeq.contains(r.getId)).asJava,
          allIngredients.filter(r => user.getUnavailable.asScala.map(_.getId).toSeq.contains(r.getId)).asJava,
          storage
        )
      )

      val historyEntryPoint: EntryPoint = session.getEntryPoint("$history")

      historyRepo.getHistory(userId)
        .foreach(h =>
          historyEntryPoint.insert(new MadeRecipe(
            allRecipes.find(_.getId == h.getRecipe.getId).get,
            h.getDate,
            h.getServings.toInt
          ))
        )

      logger.info(s"KIE Session initialized for user $userId.")

    }

  }

  override def removeSession(userId: Long): Unit = {
    logger.info(s"Removed session for user $userId.")
    sessions.invalidateSimpleSession(userId)
  }

  override def recommendOnHomepage(userId: Long): Option[Recommendation] = {
    val session: KieSession = sessions.simpleSession(userId)

    val recommendations: Seq[Recommendation] = session
      .getQueryResults("AllRecommendations")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq

    val started: Seq[StartedEngine] = session
      .getQueryResults("Started")
      .iterator()
      .asScala
      .map(r => r.get("$started").asInstanceOf[StartedEngine])
      .toSeq

    if(recommendations.size == 0 && started.size == 0) {
      session.getAgenda().getAgendaGroup("Recommendation").setFocus()

      session.getAgenda().getAgendaGroup("Conclusion").setFocus()

      session.fireAllRules

      session.insert(new StartedEngine())
    }

    val results: Option[Recommendation] = session
      .getQueryResults("SimpleResults")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq.headOption

    results.foreach(r => logger.info(s"User $userId might like recipe " + r.getRecipeId))
    results.map(session.getFactHandle(_)).foreach(session.delete)

    results
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
