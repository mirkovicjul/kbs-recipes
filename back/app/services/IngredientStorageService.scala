package services


import com.typesafe.scalalogging.LazyLogging
import database.{IngredientRepo, IngredientStorageRepo, MeasurementRepo, UserRepo}
import drools.SessionCache
import models.{IngredientScala, IngredientStorage, User}

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


trait IngredientStorageService {
  def getIngredientStorage(userId: Long): Seq[IngredientStorage]

  def addIngredientToStorage(userId: Long, ingredientId: Long, measurementId: Long, quantity: Long, date: String): Unit

}

class IngredientStorageServiceImpl @Inject()(ingredientStorageRepo: IngredientStorageRepo,
                                             userRepo: UserRepo,
                                             ingredientRepo: IngredientRepo,
                                             measurementRepo: MeasurementRepo,
                                             sessions: SessionCache)(implicit ec: ExecutionContext) extends IngredientStorageService with LazyLogging{

  override def getIngredientStorage(userId: Long): Seq[IngredientStorage] = {
    logger.debug("Before ingredient repo")
    val res = ingredientStorageRepo.getIngredientStorage(userId)
    logger.debug("After ingredient repo")

    res
  }

  override def addIngredientToStorage(userId: Long, ingredientId: Long, measurementId: Long, quantity: Long, date: String): Unit = {
      val user: User = userRepo.getByid(userId).get
      val ingredient = ingredientRepo.one(ingredientId).get
      val measurement = measurementRepo.one(measurementId).get
      val formatter = DateTimeFormatter.ofPattern("yyyy-M-d")
      val bestBefore = LocalDate.parse(date, formatter)
      ingredientStorageRepo.addNewIngredientToStorage(new IngredientStorage(ingredient, user, quantity, measurement, bestBefore))
      sessions.addFactStorageIngredient(userId, ingredientId, quantity, measurementId, bestBefore)
  }
}
