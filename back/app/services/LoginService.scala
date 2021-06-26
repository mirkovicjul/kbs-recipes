package services

import com.github.t3hnar.bcrypt._
import database.UserRepo
import models.{LoginResponse, User}
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.libs.json.Json

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

trait LoginService {

  def login(username: String, password: String): Future[LoginResponse]

  def createToken(user: User): Future[String]

}

class LoginServiceImpl @Inject()(
    userRepo: UserRepo,
    recommendationService: RecommendationService
)(implicit ec: ExecutionContext)
    extends LoginService {

  override def login(
      username: String,
      password: String
  ): Future[LoginResponse] =
    userRepo.getByUsername(username).flatMap {
      case Some(user) if password.isBcrypted(user.getPassword) =>
        createToken(user).map { token =>
          recommendationService.initSession(user.getId)
          LoginResponse(true, token, "Successfully logged in")
        }
      case Some(user) if !password.isBcrypted(user.getPassword) =>
        Future {
          LoginResponse(false, "", "Invalid username or password")
        }
      case None => {
        Future {
          LoginResponse(false, "", "Invalid username or password")
        }
      }
    }

  override def createToken(user: User): Future[String] = {
    Future {
      val claim = Json.obj(("user", user.getUsername), ("userId", user.getId.longValue()) , ("type", user.getUserType.longValue()))
      val key   = "secretKey"
      val algo  = JwtAlgorithm.HS256
      JwtJson.encode(claim, key, algo)
    }
  }

}
