package controllers

import models.User
import play.api.mvc._
import services.UserService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
    val controllerComponents: ControllerComponents,
    userService: UserService
)(implicit ec: ExecutionContext)
    extends BaseController {

  def getUser(): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val serviceResult: Future[Either[Throwable, User]] = userService.get(1)

      val formattedResult: Future[String] = serviceResult.map {
        case Left(_)      => ""
        case Right(value) => value.toString
      }

      val resultToReturn: Future[Result] = formattedResult.map(Ok(_))

      resultToReturn
  }
}
