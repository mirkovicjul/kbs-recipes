package services

import database.UserRepo
import models.User
import utils.RepoError

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

trait UserService {

  def get(id: Long): Future[Either[Throwable, User]]

}

class UserServiceImpl @Inject()(userRepo: UserRepo)(implicit ec: ExecutionContext) extends UserService {

  override def get(id: Long): Future[Either[Throwable, User]] =
    userRepo.one(id).map {
      case Some(user) =>
        Right(user)
      case None =>
        Left(RepoError(s"No user with id=$id."))
    }

}
