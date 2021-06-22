package models

import scalikejdbc._

import java.time.LocalDate

final case class RecipeScala(
    id: Long,
    name: String,
    vegan: Boolean,
    vegetarian: Boolean,
    junkFood: Boolean,
    servings: Int
)

object RecipeScala extends SQLSyntaxSupport[RecipeScala] {

  override def tableName: String = "recipes"

  override def nameConverters: Map[String, String] = Map("^name$" -> "title", "^servings$" -> "number_of_portions", "^junkFood$" -> "junk_food")

  def apply(rs: WrappedResultSet, rn: ResultName[RecipeScala]): RecipeScala =
    RecipeScala(
      id = rs.get[Long](rn.id),
      name = rs.get[String](rn.name),
      vegan = rs.get[Boolean](rn.vegan),
      vegetarian = rs.get[Boolean](rn.vegan),
      junkFood = rs.get[Boolean](rn.junkFood),
      servings = rs.get[Int](rn.servings)
    )

}
