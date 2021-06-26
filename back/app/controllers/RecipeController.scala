package controllers

import com.typesafe.scalalogging.LazyLogging
import controllers.RecipeController.recipeForm
import forms.RecipeForm
import models.Recipe
import play.api.Configuration
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.libs.Json
import services.RecipeService

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters.SeqHasAsJava

class RecipeController @Inject()(
    val controllerComponents: ControllerComponents,
    recipeService: RecipeService
)(implicit ec: ExecutionContext)
    extends BaseController
    with LazyLogging {

  private val Sep = java.io.File.separator

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
        val recipeImageFilepath = request.body.asMultipartFormData.flatMap { d =>
          d.file("recipeImage").map { picture =>
            picture.ref.moveTo(new java.io.File(s"public${Sep}content$Sep${UUID.randomUUID}")).getFileName.toString
          }
        }

        recipeService.saveRecipe(
          rForm.title,
          rForm.description,
          rForm.numberOfPortions,
          rForm.vegan,
          rForm.vegetarian,
          rForm.junkFood,
          rForm.daysBeforeExpiration,
          rForm.preparationTime,
          recipeImageFilepath
        )
        Ok("Successfully added new recipe!")
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
