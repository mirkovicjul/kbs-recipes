package controllers

import auth.UserAction
import com.typesafe.scalalogging.LazyLogging
import models.{HistoryItem, IngredientStorage}
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.http.HeaderNames
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.libs.Json
import services.HistoryService

import java.time.LocalDate
import scala.jdk.CollectionConverters.SeqHasAsJava
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

case class RecipeToAdd(recipeId: String, servings: String, date: String)

class HistoryController @Inject()(
                                   val controllerComponents: ControllerComponents,
                                   userAction: UserAction,
                                   historyService: HistoryService
                                 )(implicit ec: ExecutionContext)
  extends BaseController with LazyLogging {

  def getHistory(): Action[AnyContent] = userAction.async { request =>

    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)

    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) =>
        val userId = (value \ "userId").as[Long]
        val res: java.util.List[HistoryItem] = historyService.getHistory(userId).asJava
        Future(Ok(Json.toJson(res).toString))
      case None => Future(Forbidden)
    }
  }

  def addRecipeToHistory(): Action[AnyContent] = userAction.async { request =>
    val token: Option[String] = request.headers.get(HeaderNames.AUTHORIZATION)
    val json = request.body.asJson.get
    val recipeToAdd = json.as[RecipeToAdd]

    token.flatMap(t => JwtJson.decodeJson(t, "secretKey", Seq(JwtAlgorithm.HS256)).toOption) match {
      case Some(value) =>
        val userId = (value \ "userId").as[Long]
        historyService.addRecipeToHistory(userId, recipeToAdd.recipeId.toLong, recipeToAdd.servings.toLong, recipeToAdd.date)
        Future(Ok)
      case None => Future(Forbidden)
    }
  }

}


object RecipeToAdd {

  implicit val recipeToAddReads: Reads[RecipeToAdd] = (
    (JsPath \ "recipeId").read[String] and
      (JsPath \ "servings").read[String] and
      (JsPath \ "date").read[String]
    )(RecipeToAdd.apply _)

}