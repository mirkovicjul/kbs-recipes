package services

import com.typesafe.scalalogging.LazyLogging
import database.{HistoryRepo, IngredientRepo, RecipeRepo, UserRepo}
import models.HistoryItem

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.impl.Promise


trait HistoryService {
  def getHistory(userId: Long): Seq[HistoryItem]
  def addRecipeToHistory(userId: Long, recipeId: Long, servings: Long): Option[HistoryItem]
}

class HistoryServiceImpl @Inject()(historyRepo: HistoryRepo, userRepo: UserRepo, recipeRepo: RecipeRepo)(implicit ec: ExecutionContext) extends HistoryService with LazyLogging {

  override def getHistory(userId: Long): Seq[HistoryItem] = historyRepo.getHistory(userId)

  override def addRecipeToHistory(userId: Long, recipeId: Long, servings: Long): Option[HistoryItem] = {
    println("============== entered service add recipe to history")
    println("============userId " + userId)
    println("============== recipeId" + recipeId)
    println("============== servings" + servings)

    val recipe = recipeRepo.recipeById(recipeId)
    val date = LocalDate.now()
    println("============== date" + date)

    userRepo.one(userId).map(x => x match {
        case Some(value) =>
          historyRepo.saveHistoryItem(new HistoryItem(value, recipe, servings, date))
        case None =>
      })
    None
  }
}
