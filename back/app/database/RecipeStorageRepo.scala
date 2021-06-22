package database

import io.ebean.Finder
import models.RecipeStorage

import scala.jdk.CollectionConverters.ListHasAsScala


trait RecipeStorageRepo {

  def getRecipeStorage(userId: Long): Seq[RecipeStorage]

}

class RecipeStorageRepoImpl extends RecipeStorageRepo{

  private val find: Finder[Long, RecipeStorage] = new Finder[Long, RecipeStorage](classOf[RecipeStorage])

  override def getRecipeStorage(userId: Long): Seq[RecipeStorage] = find.query().where().eq("user_id", userId).findList().asScala.toSeq

}
