package services

import models.User

import scala.concurrent.Future

trait UserService {

  def get(id: Long): Future[User]

}

class UserServiceImpl extends UserService {

  override def get(id: Long): Future[User] =
    Future.successful(
      User(
        id = 1,
        username = "username-test",
        email = "test@mail.com",
        password = "kjkszpj"
      )
    )

}
