package controllers

import auth.AuthUtils
import com.typesafe.scalalogging.LazyLogging
import controllers.RecommendationController._
import drools.recommendation.Recommendation
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._
import services.RecommendationService

import javax.inject._
import scala.concurrent.ExecutionContext
import scala.jdk.CollectionConverters.CollectionHasAsScala

class RecommendationController @Inject()(
    val controllerComponents: ControllerComponents,
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
          Ok(Json.toJson(recommendations))
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
