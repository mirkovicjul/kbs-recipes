package models

import drools.recommendation.IngredientType
import scalikejdbc._

case class Ingredient(
    id: Long,
    name: String,
    fats: Double,
    carbs: Double,
    protein: Double,
    `type`: IngredientType
)

object Ingredient extends SQLSyntaxSupport[Ingredient] {

  override def tableName: String = "ingredients"

  def apply(rs: WrappedResultSet, rn: ResultName[Ingredient]): Ingredient =
    Ingredient(
      id = rs.get[Long](rn.id),
      name = rs.get[String](rn.name),
      fats = rs.get[Double](rn.fats),
      carbs = rs.get[Double](rn.carbs),
      protein = rs.get[Double](rn.protein),
      `type` = IngredientType.valueOf(rs.get[String](rn.`type`))
    )

}
