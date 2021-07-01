package services

import com.typesafe.scalalogging.LazyLogging
import database.{HistoryRepo, IngredientRepo, RecipeRepo, UserRepo}
import drools.SessionCache
import models.HistoryItem

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import java.time.format.DateTimeFormatter

trait HistoryService {

  def getHistory(userId: Long): Seq[HistoryItem]

  def addRecipeToHistory(userId: Long, recipeId: Long, servings: Long, date: String): Option[HistoryItem]

}

class HistoryServiceImpl @Inject()(historyRepo: HistoryRepo, userRepo: UserRepo, recipeRepo: RecipeRepo, sessions: SessionCache)(implicit ec: ExecutionContext) extends HistoryService with LazyLogging {

  override def getHistory(userId: Long): Seq[HistoryItem] = historyRepo.getHistory(userId)

  override def addRecipeToHistory(userId: Long, recipeId: Long, servings: Long, date: String): Option[HistoryItem] = {
    println(s"Adding recipe to history for user ${userId}, recipe ${recipeId}")

    val recipe = recipeRepo.recipeById(recipeId)

    val formatter = DateTimeFormatter.ofPattern("yyyy-M-d")
    val d = LocalDate.parse(date, formatter)

    userRepo.one(userId).map(x => x match {
        case Some(value) =>
          historyRepo.saveHistoryItem(new HistoryItem(value, recipe, servings, d))
          sessions.addFactHistoryItem(userId, recipeId, servings, d)
      })
    None
  }
}
