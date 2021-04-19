package modules

import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import services.{LoginService, LoginServiceImpl}

class RecipeModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): collection.Seq[Binding[_]] =
    List(
      bind[LoginService].to(classOf[LoginServiceImpl]).eagerly()
    )
}
