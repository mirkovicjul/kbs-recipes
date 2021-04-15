package controllers

import play.api.mvc._
import services.LoginService

import javax.inject._

@Singleton
class LoginController @Inject()(
    val controllerComponents: ControllerComponents,
    loginService: LoginService
) extends BaseController {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(loginService.login("", "").toString)
  }
}
