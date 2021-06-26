package controllers

import com.typesafe.scalalogging.LazyLogging
import controllers.RecipeController.recipeForm
import forms.RecipeForm
import models.Recipe
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
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

  def getAllRecipes(): Action[AnyContent] = Action.async {
    val res: java.util.List[Recipe] = recipeService.getAllRecipes().asJava
    Future(Ok(Json.toJson(res).toString))

  }

  def getRecipeById(id: Long): Action[AnyContent] = Action.async {
    val res = recipeService.getRecipeById(id)
    Future(Ok(Json.toJson(res).toString))
  }

  def addRecipe(): Action[AnyContent] = Action.apply { implicit request =>
    recipeForm.bindFromRequest.fold(
      (err: Form[RecipeForm]) => {
        logger.warn("Errors while validating form.", err.errors.map(_.message))
        BadRequest
      },
      (rForm: RecipeForm) => {
        recipeService.saveRecipe(
          rForm.title,
          rForm.description,
          rForm.numberOfPortions,
          rForm.vegan,
          rForm.vegetarian,
          rForm.junkFood,
          rForm.daysBeforeExpiration,
          rForm.preparationTime
        )
        Ok
      }
    )
  }

}

object RecipeController {

  val recipeForm: Form[RecipeForm] = Form(
    mapping(
      "title"                -> text,
      "description"          -> text,
      "numberOfPortions"     -> number,
      "vegan"                -> boolean,
      "vegetarian"           -> boolean,
      "junkFood"             -> boolean,
      "daysBeforeExpiration" -> number,
      "preparationTime"      -> number
    )(RecipeForm.apply)(RecipeForm.unapply)
  )

}
