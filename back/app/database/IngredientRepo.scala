package database

import models.{IngredientScala, RecipeIngredientScala}
import scalikejdbc._

trait IngredientRepo {

  def allIngredients(): Seq[IngredientScala]

  def recipeIngredients(recipeId: Long): Seq[RecipeIngredientScala]

}

class IngredientRepoImpl extends IngredientRepo {

  val is = IngredientScala.syntax
  val ic = IngredientScala.column

  val ris = RecipeIngredientScala.syntax
  val ric = RecipeIngredientScala.column

  override def allIngredients(): Seq[IngredientScala] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(IngredientScala.as(is))
      }.map(result => IngredientScala(result, is.resultName)).list().apply()
    }

  override def recipeIngredients(recipeId: Long): Seq[RecipeIngredientScala] =
    DB readOnly { implicit session =>
      withSQL {
        select
          .from(RecipeIngredientScala.as(ris))
          .where
          .eq(ric.recipeId, recipeId)
      }.map(result => RecipeIngredientScala(result, ris.resultName)).list().apply()
    }

}
