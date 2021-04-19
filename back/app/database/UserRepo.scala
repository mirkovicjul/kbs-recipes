package database

import scalikejdbc._
import models.User

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserRepo {

  def one(id: Long): Future[Option[User]]

}

class UserRepoPostgres extends UserRepo {

  implicit val session: DBSession = AutoSession

  val u: QuerySQLSyntaxProvider[SQLSyntaxSupport[User], User] = User.syntax

  override def one(id: Long): Future[Option[User]] =
    Future {
      DB readOnly { implicit session =>
        withSQL {
          select
            .from(User.as(u))
            .where
            .eq(u.id, id)
        }.map(result => User(result)).first.apply
      }
    }

}
