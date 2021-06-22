package services


import com.typesafe.scalalogging.LazyLogging
import database.IngredientStorageRepo
import models.{IngredientScala, IngredientStorage}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


trait IngredientStorageService {
  def getIngredientStorage(userId: Long): Seq[IngredientStorage]
}

class IngredientStorageServiceImpl @Inject()(ingredientStorageRepo: IngredientStorageRepo)(implicit ec: ExecutionContext) extends IngredientStorageService with LazyLogging{

  override def getIngredientStorage(userId: Long): Seq[IngredientStorage] = {
    logger.debug("Before ingredient repo")
    val res = ingredientStorageRepo.getIngredientStorage(userId)
    logger.debug("After ingredient repo")

    res
  }
}
