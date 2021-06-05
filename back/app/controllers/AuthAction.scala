package controllers

import com.typesafe.scalalogging.LazyLogging
import controllers.AuthAction.parseBearerToken
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.http.HeaderNames
import play.api.mvc.Results.{Forbidden, Ok}
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import scala.util.matching.Regex

class AuthAction @Inject()(
    parser: BodyParsers.Default
)(implicit ec: ExecutionContext)
    extends ActionBuilderImpl(parser)
    with LazyLogging {

  override def invokeBlock[A](
      request: Request[A],
      block: Request[A] => Future[Result]
  ): Future[Result] =
    request.headers
      .get(HeaderNames.AUTHORIZATION)
      match {
      case Some(token) =>{
        JwtJson.decodeJson(token, "secretKey", Seq(JwtAlgorithm.HS256)) match {
          case Failure(exception) => Future.successful(Forbidden)
          case Success(value) => Future.successful(Ok)
        }}
        // check if token is valid
        // otherwise Forbidden
        block(request)
      case None =>
        logger.debug(s"Missing auth token in request ${request.path}")
        Future.successful(Forbidden)
    }

}

object AuthAction {

  private val BearerRegex: Regex = "Bearer ([a-zA-Z0-9.]*)".r("tokenMatch")

  private def parseBearerToken(value: String): Option[String] =
    BearerRegex.findFirstMatchIn(value).map(_.group("tokenMatch"))

}
