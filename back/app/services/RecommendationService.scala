package services

import com.typesafe.scalalogging.LazyLogging
import drools.SessionCache
import drools.recommendation.Recommendation

import javax.inject.Inject
import scala.jdk.CollectionConverters.IteratorHasAsScala

//final case class Ingredient(name: String)

trait RecommendationService {

  def recommend(userId: Long): Seq[Recommendation]

}

class RecommendationServiceImpl @Inject()(sessions: SessionCache)
    extends RecommendationService
    with LazyLogging {

  override def recommend(userId: Long): Seq[Recommendation] = {

    val session = sessions.simpleSession(userId)

    session.fireAllRules

    session
      .getQueryResults("SimpleResults")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq
  }

}
