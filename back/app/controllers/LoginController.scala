package controllers

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, JsValue, Json, Reads}
import play.api.mvc._
import services.LoginService

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

case class UserLogin(username: String, password: String)

@Singleton
class LoginController @Inject()(
    val controllerComponents: ControllerComponents,
    loginService: LoginService
)(implicit ec: ExecutionContext) extends BaseController {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def login() = Action.async { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val user = json.as[UserLogin]

    val serviceResult: Future[JsValue] = loginService.login(user.username, user.password).map(r => Json.toJson(r))

    val resultToReturn: Future[Result] = serviceResult.map(x => Ok(x))

    resultToReturn
  }
}

object UserLogin {

  implicit val userLoginReads: Reads[UserLogin] = (
    (JsPath \ "username").read[String] and
      (JsPath \ "password").read[String]
    )(UserLogin.apply _)

}