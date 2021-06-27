package services

import com.typesafe.scalalogging.LazyLogging
import database.{IngredientRepo, RecipeRepo}
import forms.RecipeForm
import models.{Recipe, RecipeIngredient}

import java.util.{ArrayList => JArrayList}
import javax.inject.Inject

trait RecipeService {

  def getAllRecipes(): Seq[Recipe]

  def getRecipeById(id: Long): Recipe

  def saveRecipe(recipeForm: RecipeForm): Option[Recipe]

}

class RecipeServiceImpl @Inject()(
    recipeRepo: RecipeRepo,
    ingredientRepo: IngredientRepo
) extends RecipeService
    with LazyLogging {

  override def getAllRecipes(): Seq[Recipe] = recipeRepo.allRecipes()

  override def getRecipeById(id: Long): Recipe = recipeRepo.recipeById(id)

  override def saveRecipe(recipeForm: RecipeForm): Option[Recipe] = {

    val recipe = new Recipe(
      recipeForm.title,
      recipeForm.description,
      recipeForm.numberOfPortions,
      recipeForm.vegan,
      recipeForm.vegetarian,
      recipeForm.junkFood,
      recipeForm.daysBeforeExpiration,
      recipeForm.preparationTime,
      new JArrayList[RecipeIngredient](),
      recipeForm.image.orNull
    )

    Option(recipeRepo.saveRecipe(recipe))
  }

}
