package database

import io.ebean.Finder
import models.{Recipe, RecipeScala}
import scalikejdbc._

import scala.jdk.CollectionConverters.ListHasAsScala

trait RecipeRepo {

  def allRecipesScala(): Seq[RecipeScala]

  def allRecipes(): Seq[Recipe]

  def recipeById(id: Long): Recipe

}

class RecipeRepoImpl extends RecipeRepo {

  val rs = RecipeScala.syntax

  private val find: Finder[Long, Recipe] = new Finder[Long, Recipe](classOf[Recipe])

  override def allRecipesScala(): Seq[RecipeScala] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(RecipeScala.as(rs))
      }.map(result => RecipeScala(result, rs.resultName)).list().apply()
    }

  override def allRecipes(): Seq[Recipe] = find.all().asScala.toList

  override def recipeById(id: Long): Recipe = find.byId(id)

}
