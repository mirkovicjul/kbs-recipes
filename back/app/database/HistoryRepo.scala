package database

import io.ebean.Finder
import models.HistoryItem

import scala.concurrent.Future
import scala.jdk.CollectionConverters.ListHasAsScala

trait HistoryRepo {

  def getHistory(userId: Long): Seq[HistoryItem]

  def saveHistoryItem(historyItem: HistoryItem): HistoryItem
}

class HistoryRepoImpl extends HistoryRepo {

  private val find: Finder[Long, HistoryItem] = new Finder[Long, HistoryItem](classOf[HistoryItem])

  override def getHistory(userId: Long): Seq[HistoryItem] = find.query().where().eq("user_id", userId).findList().asScala.toSeq

  override def saveHistoryItem(historyItem: HistoryItem): HistoryItem  = {
    historyItem.save()
    historyItem
  }

}
