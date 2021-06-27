package services

import database.{IngredientRepo, UserRepo}
import models.{Ingredient, User}
import utils.RepoError

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import com.github.t3hnar.bcrypt._

import scala.concurrent.impl.Promise

trait UserService {

  def get(id: Long): Future[Either[Throwable, User]]
  def create(username: String, email: String, password: String): Future[Either[Throwable, Long]]
  def checkIfUsernameAvailable(username: String): Future[Boolean]
  def checkIfEmailAvailable(email: String): Future[Boolean]
  def encryptPassword(password: String): Future[Option[String]]
  def addLike(user: User, ingredientId: Long): Unit
  def addDislike(user: User, ingredientId: Long): Unit
  def addAllergy(user: User, ingredientId: Long): Unit
  def addUnavailable(user: User, ingredientId: Long): Unit
}

class UserServiceImpl @Inject()(userRepo: UserRepo, ingredientRepo: IngredientRepo)(implicit ec: ExecutionContext) extends UserService {

  override def get(id: Long): Future[Either[Throwable, User]] =
    userRepo.one(id).map {
      case Some(user) =>
        Right(user)
      case None =>
        Left(RepoError(s"No user with id=$id."))
    }

  override def checkIfUsernameAvailable(username: String) = {
    userRepo.getByUsername(username).map {
      case Some(user: User) =>
        false
      case None =>
        true
    }
  }

  override def checkIfEmailAvailable(email: String) = userRepo.getByEmail(email).map {
    case Some(user: User) =>
      false
    case None =>
      true
  }

  override def encryptPassword(password: String): Future[Option[String]] = Future {
    password.bcryptSafe(5).toOption
  }

  override def create(username: String, email: String, password: String): Future[Either[Throwable, Long]] = {
  checkIfUsernameAvailable(username).flatMap{ usernameAvailable =>
      checkIfEmailAvailable(email).flatMap{ emailAvailable =>
        if (usernameAvailable && emailAvailable) encryptPassword(password).flatMap {
          case Some(encryptedPassword) => {
            userRepo.create(username, email, encryptedPassword).map { id =>
              Right(id)
            }
          }
          case None =>
            Future {
              Left(RepoError("Something went wrong"))
            }
        } else {
          Future {
            Left(RepoError("Username or email already in use"))
          }
        }
      }}
  }

  override def addLike(user: User, ingredientId: Long) = {
    val likes = user.getLikes
    ingredientRepo.one(ingredientId) match {
      case Some(value) => {
        likes.add(value)
        user.setLikes(likes)
        userRepo.save(user)
      }
      case None =>
    }
  }

  override def addDislike(user: User, ingredientId: Long) = {
    val dislikes = user.getDislikes
    ingredientRepo.one(ingredientId) match {
      case Some(value) => {
        dislikes.add(value)
        user.setDislikes(dislikes)
        userRepo.save(user)
      }
      case None =>
    }
  }

  override def addAllergy(user: User, ingredientId: Long) = {
    val allergies = user.getAllergies
    ingredientRepo.one(ingredientId) match {
      case Some(value) => {
        allergies.add(value)
        user.setAllergies(allergies)
        userRepo.save(user)
      }
      case None =>
    }
  }

  override def addUnavailable(user: User, ingredientId: Long) = {
    val unavailable = user.getUnavailable
    ingredientRepo.one(ingredientId) match {
      case Some(value) => {
        unavailable.add(value)
        user.setUnavailable(unavailable)
        userRepo.save(user)
      }
      case None =>
    }
  }

}
