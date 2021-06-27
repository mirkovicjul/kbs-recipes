package controllers

import com.typesafe.scalalogging.LazyLogging
import forms._
import models.Recipe
import play.api.mvc._
import play.libs.Json
import services.RecipeService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters.SeqHasAsJava

class RecipeController @Inject()(
    val controllerComponents: ControllerComponents,
    recipeService: RecipeService
)(implicit ec: ExecutionContext)
    extends BaseController
    with LazyLogging {

  def getAllRecipes: Action[AnyContent] = Action.async {
    val res: java.util.List[Recipe] = recipeService.getAllRecipes().asJava
    Future(Ok(Json.toJson(res).toString))
  }

  def getRecipeById(id: Long): Action[AnyContent] = Action.async {
    val res = recipeService.getRecipeById(id)
    Future(Ok(Json.toJson(res).toString))
  }

  def addRecipe(): Action[AnyContent] = Action.apply { implicit request =>
    request.body.asJson.flatMap(_.asOpt[RecipeForm]) match {
      case Some(recipeForm) =>
        recipeService.saveRecipe(recipeForm).fold(InternalServerError)(_ => Ok)
      case None =>
        BadRequest
    }
  }

}
