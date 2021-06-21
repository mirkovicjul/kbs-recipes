package database

import io.ebean.Finder
import models.{IngredientStorage, User}

import scala.jdk.CollectionConverters.ListHasAsScala

trait IngredientStorageRepo {

  def getIngredientStorage(userId: Long): Seq[IngredientStorage]

}

class IngredientStorageRepoImpl extends IngredientStorageRepo {

  private val find: Finder[Long, IngredientStorage] = new Finder[Long, IngredientStorage](classOf[IngredientStorage])

  override def getIngredientStorage(userId: Long): Seq[IngredientStorage] = find.query().where().eq("user_id", userId).findList().asScala.toSeq

}
