package controllers

import models.User
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}
import play.api.mvc._
import services.UserService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

case class UserRegistration(username: String, email: String, password: String)

@Singleton
class UserController @Inject()(
    val controllerComponents: ControllerComponents,
    authAction: AuthAction,
    userService: UserService
)(implicit ec: ExecutionContext)
    extends BaseController {

  def testAuth(): Action[AnyContent] = authAction.async {
    Future.successful(Ok)
  }

  def getUser(): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val serviceResult: Future[Either[Throwable, User]] = userService.get(1)

      val formattedResult: Future[String] = serviceResult.map {
        case Left(_) => "No such user"
        case Right(value) => value.toString
      }

      val resultToReturn: Future[Result] = formattedResult.map(Ok(_))

      resultToReturn
  }

  def register(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val user = json.as[UserRegistration]

    val serviceResult = userService.create(user.username, user.email, user.password)
    val resultToReturn = serviceResult.map(x => Ok(x.toString))

    resultToReturn
  }
}

 object UserRegistration {

   implicit val registerUserReads: Reads[UserRegistration] = (
     (JsPath \ "username").read[String] and
       (JsPath \ "email").read[String] and
       (JsPath \ "password").read[String]

     )(UserRegistration.apply _)
}
