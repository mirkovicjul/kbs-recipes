package models

import scalikejdbc._

case class RecipeIngredient(
    ingredientId: Long,
    recipeId: Long,
    measurementId: Long,
    amount: Double
)

object RecipeIngredient extends SQLSyntaxSupport[RecipeIngredient] {

  override def tableName: String = "recipe_ingredient"

  def apply(rs: WrappedResultSet, rn: ResultName[RecipeIngredient]): RecipeIngredient =
    RecipeIngredient(
      ingredientId = rs.get[Long](rn.ingredientId),
      recipeId = rs.get[Long](rn.recipeId),
      measurementId = rs.get[Long](rn.measurementId),
      amount = rs.get[Double](rn.amount)
    )

}
