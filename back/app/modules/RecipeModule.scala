package modules

import database.{IngredientRepo, IngredientRepoImpl, IngredientStorageRepo, IngredientStorageRepoImpl, MeasurementRepo, MeasurementRepoImpl, RecipeRepo, RecipeRepoImpl, UserRepo, UserRepoPostgres}
import drools.{SessionCache, SessionCacheImpl}
import org.kie.api.runtime.KieContainer
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import services.{IngredientStorageService, IngredientStorageServiceImpl, LoginService, LoginServiceImpl, RecommendationService, RecommendationServiceImpl, UserService, UserServiceImpl}

class RecipeModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): collection.Seq[Binding[_]] =
    List(
      // services
      bind[LoginService].to(classOf[LoginServiceImpl]).eagerly(),
      bind[UserService].to(classOf[UserServiceImpl]).eagerly(),
      bind[RecommendationService].to(classOf[RecommendationServiceImpl]).eagerly(),
      bind[IngredientStorageService].to(classOf[IngredientStorageServiceImpl]).eagerly(),

      // repos
      bind[UserRepo].to(classOf[UserRepoPostgres]).eagerly(),
      bind[RecipeRepo].to(classOf[RecipeRepoImpl]).eagerly(),
      bind[IngredientRepo].to(classOf[IngredientRepoImpl]).eagerly(),
      bind[MeasurementRepo].to(classOf[MeasurementRepoImpl]).eagerly(),
      bind[IngredientStorageRepo].to(classOf[IngredientStorageRepoImpl]).eagerly(),

      // utils
      bind[KieContainer].toProvider(classOf[KieContainerProvider]).eagerly(),
      bind[SessionCache].to(classOf[SessionCacheImpl]).eagerly()
    )
}
