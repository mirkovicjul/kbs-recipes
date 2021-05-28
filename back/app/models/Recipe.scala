package models

import scalikejdbc._

import java.time.LocalDate

final case class Recipe(
    id: Long,
    name: String,
    vegan: Boolean,
    vegetarian: Boolean,
    junkFood: Boolean,
    servings: Int
)

object Recipe extends SQLSyntaxSupport[Recipe] {

  override def tableName: String = "recipes"

  override def nameConverters: Map[String, String] = Map("^name$" -> "title", "^servings$" -> "number_of_portions", "^junkFood$" -> "junk_food")

  def apply(rs: WrappedResultSet, rn: ResultName[Recipe]): Recipe =
    Recipe(
      id = rs.get[Long](rn.id),
      name = rs.get[String](rn.name),
      vegan = rs.get[Boolean](rn.vegan),
      vegetarian = rs.get[Boolean](rn.vegan),
      junkFood = rs.get[Boolean](rn.junkFood),
      servings = rs.get[Int](rn.servings)
    )

}
