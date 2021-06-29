package controllers

import auth.UserAction
import com.typesafe.scalalogging.LazyLogging
import models.User
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.http.HeaderNames
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}
import play.api.mvc._
import play.libs.Json
import services.{RecommendationService, UserService}
import utils.RepoError

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

case class UserRegistration(username: String, email: String, password: String)

@Singleton
class UserController @Inject()(
                                val controllerComponents: ControllerComponents,
                                userAction: UserAction,
                                userService: UserService,
                                recommendationService: RecommendationService
                              )(implicit ec: ExecutionContext)
  extends BaseController
    with LazyLogging {

  def testAuth(): Action[AnyContent] = userAction.async { req =>
    logger.debug("Inside test auth.")
    Future.successful(Ok)
  }

  def getUser(): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val serviceResult: Future[Either[Throwable, User]] = userService.get(2)

      val formattedResult: Future[String] = serviceResult.map {
        case Left(_) => "No such user"
        case Right(value) => value.toString
      }

      val resultToReturn: Future[Result] = formattedResult.map(Ok(_))

      resultToReturn
  }

  def getUserById(): Action[AnyContent] = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)

    token.flatMap(
      t =>
        JwtJson
          .decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256))
          .toOption) match {
      case Some(value) =>
        val userId = (value \ "userId").as[Long]
        val serviceResult: Future[Either[Throwable, User]] = userService.get(userId)
        serviceResult.map {
          case Left(_) => Ok("No such user")
          case Right(value) => Ok(Json.toJson(value).toString)
        }
      case None => Future(Forbidden)
    }
  }

  def register(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val user = json.as[UserRegistration]

    val serviceResult: Future[Result] = userService.create(user.username, user.email, user.password).map {
      case Left(RepoError(msg)) => Ok("{ \"success\": false," +
        "\"message\":" + msg + "}")
      case Right(value) => Ok("{ \"success\": true," +
        "\"message\":\"\"}")
      case _ => InternalServerError
    }

    serviceResult

  }

  def addLike() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.addLike(user, ingredientId.toLong)
            //recommendationService.removeSession(userId)
            //recommendationService.initSession(userId)
            recommendationService.addNewFactLike(1, userId, ingredientId.toLong)

          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }
  }

  def removeLike() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.removeLike(user, ingredientId.toLong)
            recommendationService.removeSession(userId)
            recommendationService.initSession(userId)

          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }
  }

  def addDislike() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.addDislike(user, ingredientId.toLong)
            //recommendationService.removeSession(userId)
            //recommendationService.initSession(userId)
            recommendationService.addNewFactLike(2, userId, ingredientId.toLong)
          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }
  }

  def removeDislike() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.removeDislike(user, ingredientId.toLong)
            recommendationService.removeSession(userId)
            recommendationService.initSession(userId)

          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }
  }

  def addAllergy() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.addAllergy(user, ingredientId.toLong)
            //recommendationService.removeSession(userId)
            //recommendationService.initSession(userId)
            recommendationService.addNewFactLike(3, userId, ingredientId.toLong)

          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }
  }

  def removeAllergy() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.removeAllergy(user, ingredientId.toLong)
            recommendationService.removeSession(userId)
            recommendationService.initSession(userId)

          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }
  }

  def addUnavailable() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.addUnavailable(user, ingredientId.toLong)
            //recommendationService.removeSession(userId)
            //recommendationService.initSession(userId)
            recommendationService.addNewFactLike(4, userId, ingredientId.toLong)
          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }
  }

  def removeUnavailable() = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val ingredientId = request.body.asJson.flatMap(j => (j \ "ingredientId").asOpt[String]).get
    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) => {
        val userId = (value \ "userId").as[Long]
        userService.get(userId).map {
          case Left(value) => Future(Forbidden)
          case Right(user) => {
            userService.removeUnavailable(user, ingredientId.toLong)
            recommendationService.removeSession(userId)
            recommendationService.initSession(userId)

          }
        }
      }
        Future(Ok)

      case None => Future(Forbidden)
    }

  }
}


object UserRegistration {

  implicit val registerUserReads: Reads[UserRegistration] = (
    (JsPath \ "username").read[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "password").read[String]
    ) (UserRegistration.apply _)
}
