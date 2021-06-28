package controllers

import com.typesafe.scalalogging.LazyLogging
import models.{Ingredient, Recipe}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.libs.Json
import services.IngredientService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters.SeqHasAsJava

class IngredientController @Inject()(
                                       val controllerComponents: ControllerComponents,
                                       ingredientService: IngredientService
                                     )(implicit ec: ExecutionContext) extends BaseController with LazyLogging {

  def getAllIngredients: Action[AnyContent] = Action.async {
    val res: java.util.List[Ingredient] = ingredientService.allIngredients().asJava
    Future(Ok(Json.toJson(res).toString))
  }

}