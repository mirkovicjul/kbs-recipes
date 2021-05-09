package modules

import database.{UserRepo, UserRepoPostgres}
import drools.{SessionCache, SessionCacheImpl}
import org.kie.api.runtime.KieContainer
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import services.{LoginService, LoginServiceImpl, RecommendationService, RecommendationServiceImpl, UserService, UserServiceImpl}

class RecipeModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): collection.Seq[Binding[_]] =
    List(
      bind[LoginService].to(classOf[LoginServiceImpl]).eagerly(),
      bind[UserService].to(classOf[UserServiceImpl]).eagerly(),
      bind[UserRepo].to(classOf[UserRepoPostgres]).eagerly(),
      bind[RecommendationService].to(classOf[RecommendationServiceImpl]).eagerly(),

      bind[KieContainer].toProvider(classOf[KieContainerProvider]).eagerly(),
      bind[SessionCache].to(classOf[SessionCacheImpl]).eagerly()
    )
}
