package services

import com.typesafe.scalalogging.LazyLogging
import database.RecipeRepo
import models.Recipe

import javax.inject.Inject

trait RecipeService {
  def getAllRecipes(): Seq[Recipe]
}

class RecipeServiceImpl @Inject()(recipeRepo: RecipeRepo) extends RecipeService with LazyLogging {

  override def getAllRecipes(): Seq[Recipe] = recipeRepo.allRecipes()

}
