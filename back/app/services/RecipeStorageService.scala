package services

import com.typesafe.scalalogging.LazyLogging
import database.RecipeStorageRepo
import models.RecipeStorage

import javax.inject.Inject

trait RecipeStorageService {
  def getRecipeStorage(userId: Long): Seq[RecipeStorage]
}

class RecipeStorageServiceImpl @Inject()(recipeStorageRepo: RecipeStorageRepo) extends RecipeStorageService with LazyLogging{

  override def getRecipeStorage(userId: Long): Seq[RecipeStorage] = recipeStorageRepo.getRecipeStorage(userId)

}