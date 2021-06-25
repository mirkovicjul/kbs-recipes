package models

import scalikejdbc._

case class RecipeIngredientScala(
    ingredientId: Long,
    recipeId: Long,
    measurementId: Long,
    amount: Double
)

object RecipeIngredientScala extends SQLSyntaxSupport[RecipeIngredientScala] {

  override def tableName: String = "recipe_ingredients"

  override def nameConverters: Map[String, String] = Map("^amount$" -> "quantity")

  def apply(rs: WrappedResultSet, rn: ResultName[RecipeIngredientScala]): RecipeIngredientScala =
    RecipeIngredientScala(
      ingredientId = rs.get[Long](rn.ingredientId),
      recipeId = rs.get[Long](rn.recipeId),
      measurementId = rs.get[Long](rn.measurementId),
      amount = rs.get[Double](rn.amount)
    )

}
