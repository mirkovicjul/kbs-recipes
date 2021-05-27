package database

import models.Recipe
import scalikejdbc._

trait RecipeRepo {

  def allRecipes(): Seq[Recipe]

}

class RecipeRepoImpl extends RecipeRepo {

  val rs = Recipe.syntax

  override def allRecipes(): Seq[Recipe] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(Recipe.as(rs))
      }.map(result => Recipe(result, rs.resultName)).list().apply()
    }

}
