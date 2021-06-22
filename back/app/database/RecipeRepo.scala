package database

import models.RecipeScala
import scalikejdbc._

trait RecipeRepo {

  def allRecipes(): Seq[RecipeScala]

}

class RecipeRepoImpl extends RecipeRepo {

  val rs = RecipeScala.syntax

  override def allRecipes(): Seq[RecipeScala] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(RecipeScala.as(rs))
      }.map(result => RecipeScala(result, rs.resultName)).list().apply()
    }

}
