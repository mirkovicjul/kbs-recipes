package models

import scalikejdbc._

case class User(
    id: Long,
    username: String,
    email: String,
    password: String
)

object User extends SQLSyntaxSupport[User] {

  override def tableName: String = "users"

  def apply(rs: WrappedResultSet): User =
    User(
      id = rs.get[Long]("id"),
      username = rs.get[String]("username"),
      email = rs.get[String]("email"),
      password = rs.get[String]("password")
    )

}
