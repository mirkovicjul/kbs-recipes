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

  def apply(rs: WrappedResultSet, rn: ResultName[User]): User =
    User(
      id = rs.get[Long](rn.id),
      username = rs.get[String](rn.username),
      email = rs.get[String](rn.email),
      password = rs.get[String](rn.password)
    )

}
