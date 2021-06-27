package database

import io.ebean.Finder
import models.User

import scala.compat.java8.OptionConverters
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserRepo {

  def one(id: Long): Future[Option[User]]

  def oneBlocking(id: Long): Option[User]

  def getByUsername(username: String): Future[Option[User]]

  def getByEmail(email: String): Future[Option[User]]

  def create(username: String, email: String, password: String): Future[Long]

  def save(user: User): Future[Long]

}

class UserRepoPostgres extends UserRepo {

  private val find: Finder[Long, User] = new Finder[Long, User](classOf[User])

  override def one(id: Long): Future[Option[User]] =
    Future {
      OptionConverters.toScala(
        find.query().where().eq("id", id).findOneOrEmpty())
    }

  override def oneBlocking(id: Long): Option[User] =
    OptionConverters.toScala(
      find.query().where().eq("id", id).findOneOrEmpty())

  override def getByUsername(username: String): Future[Option[User]] =
    Future {
      OptionConverters.toScala(
        find.query().where().eq("username", username).findOneOrEmpty())
    }

  override def getByEmail(email: String): Future[Option[User]] =
    Future {
      OptionConverters.toScala(
        find.query().where().eq("email", email).findOneOrEmpty())
    }

  override def create(
      username: String,
      email: String,
      password: String
  ): Future[Long] = {
    val u = new User(username, email, password, 1)
    u.save()
    Future.successful(u.getId)
  }

  override def save(user: User): Future[Long] = {
    user.save()
    Future.successful(user.getId)
  }

}
