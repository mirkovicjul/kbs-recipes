package services

import com.typesafe.scalalogging.LazyLogging
import database.UserRepo
import drools.SessionCache
import drools.recommendation.Recommendation

import javax.inject.Inject
import scala.jdk.CollectionConverters.IteratorHasAsScala

trait RecommendationService {

  def initSession(userId: Long): Unit

  def recommend(userId: Long): Option[Recommendation]

}

class RecommendationServiceImpl @Inject()(
    sessions: SessionCache,
    userRepo: UserRepo
) extends RecommendationService
    with LazyLogging {

  override def initSession(userId: Long): Unit = {
    val session = sessions.simpleSession(userId)

    // TODO: insert facts
  }

  override def recommend(userId: Long): Option[Recommendation] = {
    val session = sessions.simpleSession(userId)

    session.fireAllRules

    session
      .getQueryResults("SimpleResults")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq
      .headOption
  }

}
