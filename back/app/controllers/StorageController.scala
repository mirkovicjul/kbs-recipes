package controllers

import auth.UserAction
import com.typesafe.scalalogging.LazyLogging
import models.{IngredientStorage, RecipeStorage}
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.http.HeaderNames
import play.api.mvc._
import play.libs.Json
import services.{IngredientStorageService, RecipeStorageService, UserService}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters.SeqHasAsJava

class StorageController @Inject()(
                                   val controllerComponents: ControllerComponents,
                                   userAction: UserAction,
                                   ingredientStorageService: IngredientStorageService,
                                   recipeStorageService: RecipeStorageService
                                 )(implicit ec: ExecutionContext)
  extends BaseController with LazyLogging {

  def getIngredientStorage(): Action[AnyContent] = userAction.async { request =>

    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)

    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) =>
        logger.debug("Before user ID")
        val userId = (value \ "userId").as[Long]
        logger.debug("After user ID")
        val res: java.util.List[IngredientStorage] = ingredientStorageService.getIngredientStorage(userId).asJava
        //ingredientStorageService.getIngredientStorage(userId).foreach(ing => println(ing.getMeasurement.getMeasurement))
        Future(Ok(Json.toJson(res).toString))

      case None => Future(Forbidden)
    }

  }

  def getRecipeStorage(): Action[AnyContent] = userAction.async { request =>

    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)

    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) =>
        val userId = (value \ "userId").as[Long]
        val res: java.util.List[RecipeStorage] = recipeStorageService.getRecipeStorage(userId).asJava
        Future(Ok(Json.toJson(res).toString))
      case None => Future(Forbidden)
    }

  }
}
