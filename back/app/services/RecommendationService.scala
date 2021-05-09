package services

import com.typesafe.scalalogging.LazyLogging
import drools.SessionCache
import drools.recommendation.Recommendation

import javax.inject.Inject

final case class Ingredient(name: String)

trait RecommendationService {

  def recommend(userId: Long): List[Recommendation]

}

class RecommendationServiceImpl @Inject()(sessions: SessionCache)
    extends RecommendationService
    with LazyLogging {

  override def recommend(userId: Long): List[Recommendation] = {
    sessions.simpleSession(userId).fireAllRules

    // TODO: get results with query
    Nil
  }

}
