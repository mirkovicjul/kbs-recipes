package controllers

import drools.FireExample
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RecommendationController @Inject()(
    val controllerComponents: ControllerComponents)(
    implicit ec: ExecutionContext)
    extends BaseController {

  def regular(): Action[AnyContent] = Action.async { req =>
    FireExample.run()
    Future.successful(Ok)
  }

}
