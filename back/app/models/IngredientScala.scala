package models

import drools.recommendation.IngredientType
import scalikejdbc._

case class IngredientScala(
    id: Long,
    name: String,
    fats: Double,
    carbs: Double,
    protein: Double,
    `type`: IngredientType
)

object IngredientScala extends SQLSyntaxSupport[IngredientScala] {

  override def tableName: String = "ingredients"

  override def nameConverters: Map[String, String] = Map("^name$" -> "ingredient")

  def apply(rs: WrappedResultSet, rn: ResultName[IngredientScala]): IngredientScala =
    IngredientScala(
      id = rs.get[Long](rn.id),
      name = rs.get[String](rn.name),
      fats = rs.get[Double](rn.fats),
      carbs = rs.get[Double](rn.carbs),
      protein = rs.get[Double](rn.protein),
      `type` = IngredientType.valueOf(rs.get[String](rn.`type`))
    )

}
