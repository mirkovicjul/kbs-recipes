package modules

import database.{HistoryRepo, HistoryRepoImpl, IngredientRepo, IngredientRepoImpl, IngredientStorageRepo, IngredientStorageRepoImpl, MeasurementRepo, MeasurementRepoImpl, RecipeRepo, RecipeRepoImpl, RecipeStorageRepo, RecipeStorageRepoImpl, UserRepo, UserRepoPostgres}
import drools.{SessionCache, SessionCacheImpl}
import org.kie.api.runtime.KieContainer
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import services.{HistoryService, HistoryServiceImpl, IngredientService, IngredientServiceImpl, IngredientStorageService, IngredientStorageServiceImpl, LoginService, LoginServiceImpl, RecipeService, RecipeServiceImpl, RecipeStorageService, RecipeStorageServiceImpl, RecommendationService, RecommendationServiceImpl, UserService, UserServiceImpl}

class RecipeModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): collection.Seq[Binding[_]] =
    List(
      // services
      bind[LoginService].to(classOf[LoginServiceImpl]).eagerly(),
      bind[UserService].to(classOf[UserServiceImpl]).eagerly(),
      bind[RecommendationService].to(classOf[RecommendationServiceImpl]).eagerly(),
      bind[IngredientStorageService].to(classOf[IngredientStorageServiceImpl]).eagerly(),
      bind[RecipeStorageService].to(classOf[RecipeStorageServiceImpl]).eagerly(),
      bind[HistoryService].to(classOf[HistoryServiceImpl]).eagerly(),
      bind[RecipeService].to(classOf[RecipeServiceImpl]).eagerly(),
      bind[IngredientService].to(classOf[IngredientServiceImpl]).eagerly(),

      // repos
      bind[UserRepo].to(classOf[UserRepoPostgres]).eagerly(),
      bind[RecipeRepo].to(classOf[RecipeRepoImpl]).eagerly(),
      bind[IngredientRepo].to(classOf[IngredientRepoImpl]).eagerly(),
      bind[MeasurementRepo].to(classOf[MeasurementRepoImpl]).eagerly(),
      bind[IngredientStorageRepo].to(classOf[IngredientStorageRepoImpl]).eagerly(),
      bind[RecipeStorageRepo].to(classOf[RecipeStorageRepoImpl]).eagerly(),
      bind[HistoryRepo].to(classOf[HistoryRepoImpl]).eagerly(),
      bind[RecipeRepo].to(classOf[RecipeRepoImpl]).eagerly(),

      // utils
      bind[KieContainer].toProvider(classOf[KieContainerProvider]).eagerly(),
      bind[SessionCache].to(classOf[SessionCacheImpl]).eagerly()
    )
}
