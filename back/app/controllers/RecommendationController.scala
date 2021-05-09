package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.RecommendationService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RecommendationController @Inject()(
    val controllerComponents: ControllerComponents,
    recommendationService: RecommendationService
)(implicit ec: ExecutionContext)
    extends BaseController {

  def regular(): Action[AnyContent] = Action.async { req =>
    recommendationService.recommend(3)
    Future.successful(Ok)
  }

}
