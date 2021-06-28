package database

import com.typesafe.scalalogging.LazyLogging
import io.ebean.Finder
import models.{Ingredient, IngredientStorage, Recipe, User}

import java.util.stream.Collectors
import scala.jdk.CollectionConverters.ListHasAsScala
import java.util.{stream, List => JList}

trait IngredientStorageRepo {

  def getIngredientStorage(userId: Long): Seq[IngredientStorage]

  def getIngredientsForRecipe(recipeId: Long, userId: Long): JList[IngredientStorage]

}

class IngredientStorageRepoImpl extends IngredientStorageRepo with LazyLogging {

  private val find: Finder[Long, IngredientStorage] = new Finder[Long, IngredientStorage](classOf[IngredientStorage])
  private val findRecipe: Finder[Long, Recipe] = new Finder[Long, Recipe](classOf[Recipe])

  override def getIngredientStorage(userId: Long): Seq[IngredientStorage] =
    find.query().where().eq("user_id", userId).findList().asScala.toSeq

  override def getIngredientsForRecipe(recipeId: Long, userId: Long): JList[IngredientStorage] = {
    val recipeIngredientsStream: stream.Stream[Long] =
      findRecipe.query().where().eq("id", recipeId).findOne().getIngredients().stream().map(r => r.getIngredient().getId)

    val recipeIngredients: JList[Long] = recipeIngredientsStream.collect(Collectors.toList[Long])

    logger.info(s"Ingredients ids for recipe $recipeId are $recipeIngredients.")

    find.query().where.eq("user_id", userId).and()
      .in("ingredient_id", recipeIngredients).findList()
  }

}
