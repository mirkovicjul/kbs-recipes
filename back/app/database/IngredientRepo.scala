package database

import models.{IngredientScala, RecipeIngredient}
import scalikejdbc._

trait IngredientRepo {

  def allIngredients(): Seq[IngredientScala]

  def recipeIngredients(recipeId: Long): Seq[RecipeIngredient]

}

class IngredientRepoImpl extends IngredientRepo {

  val is = IngredientScala.syntax
  val ic = IngredientScala.column

  val ris = RecipeIngredient.syntax
  val ric = RecipeIngredient.column

  override def allIngredients(): Seq[IngredientScala] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(IngredientScala.as(is))
      }.map(result => IngredientScala(result, is.resultName)).list().apply()
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
