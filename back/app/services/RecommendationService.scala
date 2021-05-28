package services

import com.typesafe.scalalogging.LazyLogging
import database.{IngredientRepo, MeasurementRepo, RecipeRepo, UserRepo}
import drools.SessionCache
import drools.conclusion.Vegetarian
import drools.recommendation.{Recommendation, Ingredient => IngredientFact, Measurement => MeasurementFact, Recipe => RecipeFact, RecipeIngredient => RecipeIngredientFact}
import org.kie.api.runtime.KieSession

import javax.inject.Inject
import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.jdk.CollectionConverters.IteratorHasAsScala

trait RecommendationService {

  def initSession(userId: Long): Unit

  def recommend(userId: Long): Seq[Recommendation]

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
    val session: KieSession = sessions.simpleSession(userId)

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

    val allMeasurements: Seq[MeasurementFact] =
      measurementRepo
        .allMeasurements()
        .map(m => new MeasurementFact(m.id, m.measurement, m.proportion))
    allMeasurements.foreach(session.insert)

    recipeRepo
      .allRecipes()
      .foreach { recipe =>
        val recipeIngredients: Seq[RecipeIngredientFact] =
          ingredientRepo
            .recipeIngredients(recipe.id)
            .map { ri =>
              new RecipeIngredientFact(
                allIngredients.filter(_.getId == ri.ingredientId).head,
                ri.amount,
                allMeasurements.filter(_.getId == ri.measurementId).head
              )
            }

        val recipeFact = new RecipeFact(
          recipe.id,
          recipe.name,
          recipeIngredients.asJava,
          false,
          false,
          false,
          recipe.servings
        )
        session.insert(recipeFact)
      }
      val veg = new Vegetarian(1);
      session.insert(veg)

  }

  override def recommend(userId: Long): Seq[Recommendation] = {
    val session = sessions.simpleSession(userId)

    session.getAgenda().getAgendaGroup("Diet").setFocus()
    session.getAgenda().getAgendaGroup("Conclusions").setFocus()
    session.fireAllRules


    session
      .getQueryResults("SimpleResults")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq
  }

}
