package services

import com.typesafe.scalalogging.LazyLogging
import database.RecipeRepo
import models.{Recipe, RecipeIngredient}

import java.util.{ArrayList => JArrayList}
import javax.inject.Inject

trait RecipeService {

  def getAllRecipes(): Seq[Recipe]

  def getRecipeById(id: Long): Recipe

  def saveRecipe(
      title: String,
      description: String,
      numberOfPortions: Long,
      vegan: Boolean,
      vegetarian: Boolean,
      junkFood: Boolean,
      daysBeforeExpiration: Long,
      preparationTime: Long
  ): Recipe

}

class RecipeServiceImpl @Inject()(recipeRepo: RecipeRepo)
    extends RecipeService
    with LazyLogging {

  override def getAllRecipes(): Seq[Recipe] = recipeRepo.allRecipes()

  override def getRecipeById(id: Long): Recipe = recipeRepo.recipeById(id)

  override def saveRecipe(
      title: String,
      description: String,
      numberOfPortions: Long,
      vegan: Boolean,
      vegetarian: Boolean,
      junkFood: Boolean,
      daysBeforeExpiration: Long,
      preparationTime: Long
  ): Recipe =
    recipeRepo.saveRecipe(
      new Recipe(
        title,
        description,
        numberOfPortions,
        vegan,
        vegetarian,
        junkFood,
        daysBeforeExpiration,
        preparationTime,
        new JArrayList[RecipeIngredient]()
      )
    )

}
