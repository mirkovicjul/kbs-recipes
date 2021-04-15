package services

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[LoginServiceImpl])
trait LoginService {

  def login(username: String, password: String): Int

}

class LoginServiceImpl extends LoginService {

  override def login(username: String, password: String): Int = -1

}
