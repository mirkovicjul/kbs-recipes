package auth

import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.http.HeaderNames
import play.api.mvc.{AnyContent, Request}

object AuthUtils {

  def extractUserId(req: Request[AnyContent]): Option[Long] =
    req
      .headers
      .get(HeaderNames.AUTHORIZATION)
      .flatMap(token => JwtJson.decodeJson(token, "secretKey", Seq(JwtAlgorithm.HS256)).toOption)
      .flatMap(decoded => (decoded \ "userId").asOpt[Long])

}
