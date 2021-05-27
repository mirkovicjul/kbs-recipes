package database

import models.{Ingredient, RecipeIngredient}
import scalikejdbc._

trait IngredientRepo {

  def allIngredients(): Seq[Ingredient]

  def recipeIngredients(recipeId: Long): Seq[RecipeIngredient]

}

class IngredientRepoImpl extends IngredientRepo {

  val is = Ingredient.syntax
  val ic = Ingredient.column

  val ris = RecipeIngredient.syntax
  val ric = RecipeIngredient.column

  override def allIngredients(): Seq[Ingredient] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(Ingredient.as(is))
      }.map(result => Ingredient(result, is.resultName)).list().apply()
    }

  override def recipeIngredients(recipeId: Long): Seq[RecipeIngredient] =
    DB readOnly { implicit session =>
      withSQL {
        select
          .from(RecipeIngredient.as(ris))
          .where
          .eq(ric.recipeId, recipeId)
      }.map(result => RecipeIngredient(result, ris.resultName)).list().apply()
    }

}
