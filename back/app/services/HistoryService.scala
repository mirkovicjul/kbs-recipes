package services

import com.typesafe.scalalogging.LazyLogging
import database.HistoryRepo
import models.HistoryItem

import javax.inject.Inject


trait HistoryService {
  def getHistory(userId: Long): Seq[HistoryItem]
}

class HistoryServiceImpl @Inject()(historyRepo: HistoryRepo)extends HistoryService with LazyLogging {

  override def getHistory(userId: Long): Seq[HistoryItem] = historyRepo.getHistory(userId)

}
