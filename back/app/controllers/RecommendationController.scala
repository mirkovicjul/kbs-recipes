package controllers

import controllers.RecommendationController._
import drools.recommendation.Recommendation
import play.api.libs.json.{JsObject, JsPath, JsValue, Json, Reads, Writes}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, _}
import services.RecommendationService

import javax.inject._
import scala.concurrent.ExecutionContext
import scala.jdk.CollectionConverters.CollectionHasAsScala

case class RecommendationRequest(userId: Long)

class RecommendationController @Inject()(
    val controllerComponents: ControllerComponents,
    recommendationService: RecommendationService
)(implicit ec: ExecutionContext)
    extends BaseController {

  def regular(): Action[AnyContent] = Action {
    implicit req: Request[AnyContent] =>
      val requestParams: RecommendationRequest =
        req.body.asJson.get.as[RecommendationRequest]
      recommendationService.initSession(1)
      val recommendations: Seq[Recommendation] = recommendationService.recommendOnHomepage(requestParams.userId)

      Ok(Json.toJson(recommendations))
  }
}

object RecommendationController {

  implicit val recommendRequest: Reads[RecommendationRequest] =
    (JsPath \ "user_id").read[Long].map(RecommendationRequest)

  implicit val recommendation: Writes[Recommendation] = new Writes[Recommendation] {
    override def writes(o: Recommendation): JsValue =
      Json.obj(
        "recipe_id" -> o.getRecipeId.toString,
        "hit" -> o.getHit,
        "messages" -> Json.arr(o.getMessages.asScala.toSeq.map(Json.toJson[String]))
      )
  }

  implicit val recommendResponse: Writes[Seq[Recommendation]] = new Writes[Seq[Recommendation]] {
    override def writes(o: Seq[Recommendation]): JsValue =
      Json.obj("result" -> Json.arr(o.map(Json.toJson[Recommendation])))
  }


}
