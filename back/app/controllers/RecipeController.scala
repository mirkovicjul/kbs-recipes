package controllers

import com.typesafe.scalalogging.LazyLogging
import models.Recipe
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.libs.Json
import services.RecipeService

import scala.jdk.CollectionConverters.SeqHasAsJava
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RecipeController @Inject()(
                                  val controllerComponents: ControllerComponents,
                                  recipeService: RecipeService
                                )(implicit ec: ExecutionContext)
  extends BaseController with LazyLogging {

  def getAllRecipes(): Action[AnyContent] = Action.async { request =>

    val res: java.util.List[Recipe] = recipeService.getAllRecipes().asJava
    Future(Ok(Json.toJson(res).toString))

  }

}
