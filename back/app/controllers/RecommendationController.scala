package controllers

import auth.AuthUtils
import com.typesafe.scalalogging.LazyLogging
import controllers.RecommendationController._
import drools.recommendation.Recommendation
import models.Recipe
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._
import services.{RecipeService, RecommendationService}

import javax.inject._
import scala.concurrent.ExecutionContext
import scala.jdk.CollectionConverters.{CollectionHasAsScala, SeqHasAsJava}

class RecommendationController @Inject()(
    val controllerComponents: ControllerComponents,
    recipeService:RecipeService,
    recommendationService: RecommendationService
)(implicit ec: ExecutionContext)
    extends BaseController
    with LazyLogging {

  def regular(): Action[AnyContent] = Action {
    implicit req: Request[AnyContent] =>
      AuthUtils.extractUserId(req) match {
        case Some(userId) =>
          logger.info(s"Recommending recipes for user ${userId}...")
          val recommendations: Seq[Recommendation] =
            recommendationService.recommendOnHomepage(userId)
          logger.info(
            s"Recommendation finished for user ${userId}, recommended recipes ${recommendations.size}.")
          val recipes:java.util.List[Recipe] = recommendations.map(r => recipeService.getRecipeById(r.getRecipeId)).asJava
          if(recipes.size() > 0)
            (Ok(play.libs.Json.toJson(recipes.get(0)).toString()))
          else
            (Ok(play.libs.Json.toJson(recipeService.getRandomRecipe()).toString()))
        case None =>
          BadRequest
      }
  }
}

object RecommendationController {

  implicit val recommendation: Writes[Recommendation] =
    new Writes[Recommendation] {
      override def writes(o: Recommendation): JsValue =
        Json.obj(
          "recipe_id" -> o.getRecipeId.toString,
          "hit"       -> o.getHit,
          "messages" -> Json.arr(
            o.getMessages.asScala.toSeq.map(Json.toJson[String]))
        )
    }

  implicit val recommendResponse: Writes[Seq[Recommendation]] =
    new Writes[Seq[Recommendation]] {
      override def writes(o: Seq[Recommendation]): JsValue =
        Json.obj("result" -> Json.arr(o.map(Json.toJson[Recommendation])))
    }

}
