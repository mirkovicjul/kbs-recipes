package services

import database.UserRepo
import models.{LoginResponse, User}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import com.github.t3hnar.bcrypt._

import java.time.Clock
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.libs.json.Json

trait LoginService {

  def login(username: String, password: String): Future[LoginResponse]
  def createToken(user: User): Future[String]

}

class LoginServiceImpl @Inject()(userRepo: UserRepo)(implicit ec: ExecutionContext) extends LoginService {

  override def login(username: String, password: String): Future[LoginResponse] = userRepo.getByUsername(username).flatMap {
    case Some(user) => {
      if(password.isBcrypted(user.password)) {
        createToken(user).map(_.toString).map(token =>
          LoginResponse(true, token, "Successfully logged in"))
      } else {
       Future {
         LoginResponse(false, "", "Invalid username or password")}
      }
    }
    case None => {
      Future {
        LoginResponse(false, "", "Invalid username or password")
      }
    }
  }

  override def createToken(user: User): Future[String] = {
    Future {
      val claim = Json.obj(("user", user.username), ("type", user.userType))
      val key = "secretKey"
      val algo = JwtAlgorithm.HS256
      JwtJson.encode(claim, key, algo)
    }
  }

}
