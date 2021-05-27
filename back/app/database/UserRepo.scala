package database

import models.User
import scalikejdbc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserRepo {

  def one(id: Long): Future[Option[User]]

  def getByUsername(username: String): Future[Option[User]]

  def getByEmail(email: String): Future[Option[User]]

  def create(username: String, email: String, password: String): Future[Long]

}

class UserRepoPostgres extends UserRepo {

  implicit val session: DBSession = AutoSession

  val u = User.syntax
  val c = User.column

  override def one(id: Long): Future[Option[User]] = {
    Future {
      DB readOnly { implicit session =>
        withSQL {
          select
            .from(User.as(u))
            .where
            .eq(u.id, id)
        }.map(result => User(result, u.resultName)).first.apply
      }
    }
  }

  override def getByUsername(username: String): Future[Option[User]] = {
    Future {
      DB readOnly { implicit session =>
        withSQL {
          select
            .from(User.as(u))
            .where
            .eq(u.username, username)
        }.map(result => User(result, u.resultName)).first().apply()
      }
    }
  }

  override def getByEmail(email: String): Future[Option[User]] = {
    Future {
      DB readOnly { implicit session =>
        withSQL {
          select
            .from(User.as(u))
            .where
            .eq(u.email, email)
        }.map(result => User(result, u.resultName)).first().apply()
      }
    }
  }

  override def create(username: String,
                      email: String,
                      password: String): Future[Long] =
    Future {
      DB autoCommit { implicit session =>
        withSQL {
          insert
            .into(User)
            .namedValues(
              c.username -> username,
              c.email    -> email,
              c.password -> password,
              c.userType -> 1
            )
        }.updateAndReturnGeneratedKey().apply()
      }
    }

}
