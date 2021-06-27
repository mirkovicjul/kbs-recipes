package database

import io.ebean.Finder
import models.{Ingredient, IngredientScala, RecipeIngredientScala}
import scalikejdbc._

import java.util.{List => JList}
import scala.jdk.CollectionConverters.IterableHasAsJava

trait IngredientRepo {

  def allIngredientsScala(): Seq[IngredientScala]

  def recipeIngredientsScala(recipeId: Long): Seq[RecipeIngredientScala]

  def findByIds(id: Seq[Long]): JList[Ingredient]

  def one(id: Long): Option[Ingredient]

}

class IngredientRepoImpl extends IngredientRepo {

  val is = IngredientScala.syntax
  val ic = IngredientScala.column

  val ris = RecipeIngredientScala.syntax
  val ric = RecipeIngredientScala.column

  private val find: Finder[Long, Ingredient] =
    new Finder[Long, Ingredient](classOf[Ingredient])

  override def allIngredientsScala(): Seq[IngredientScala] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(IngredientScala.as(is))
      }.map(result => IngredientScala(result, is.resultName)).list().apply()
    }

  override def recipeIngredientsScala(
      recipeId: Long): Seq[RecipeIngredientScala] =
    DB readOnly { implicit session =>
      withSQL {
        select
          .from(RecipeIngredientScala.as(ris))
          .where
          .eq(ric.recipeId, recipeId)
      }.map(result => RecipeIngredientScala(result, ris.resultName))
        .list()
        .apply()
    }

  override def findByIds(ids: Seq[Long]): JList[Ingredient] =
    find.query().where().idIn(ids.asJava).findList()

  override def one(id: Long): Option[Ingredient] = Option(find.byId(id))

}
