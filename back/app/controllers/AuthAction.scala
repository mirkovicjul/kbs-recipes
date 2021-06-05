package controllers

import com.typesafe.scalalogging.LazyLogging
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.http.HeaderNames
import play.api.mvc.Results.Forbidden
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class AuthAction @Inject()(
    parser: BodyParsers.Default
)(implicit ec: ExecutionContext)
    extends ActionBuilderImpl(parser)
    with LazyLogging {

  override def invokeBlock[A](
      request: Request[A],
      block: Request[A] => Future[Result]
  ): Future[Result] =
    request.headers.get(HeaderNames.AUTHORIZATION) match {
      case Some(token) => {
        JwtJson.decodeJson(token, "secretKey", Seq(JwtAlgorithm.HS256)) match {
          case Failure(exception) =>
            logger.warn("Exception in auth middleware: ", exception)
            Future.successful(Forbidden)
          case Success(value) =>
            logger.debug(s"Value of JWT token: $value")
            block(request)
        }
      }
      case None =>
        logger.debug(s"Missing auth token in request ${request.path}")
        Future.successful(Forbidden)
    }

}
