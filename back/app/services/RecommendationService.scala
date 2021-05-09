package services

import com.typesafe.scalalogging.LazyLogging
import drools.SessionCache
import drools.recommendation.Recommendation

import java.util
import javax.inject.Inject
import scala.jdk.CollectionConverters.IteratorHasAsScala

final case class Ingredient(name: String)

trait RecommendationService {

  def recommend(userId: Long): List[Recommendation]

}

class RecommendationServiceImpl @Inject()(sessions: SessionCache)
    extends RecommendationService
    with LazyLogging {

  override def recommend(userId: Long): List[Recommendation] = {
    val session = sessions.simpleSession(userId)

    session.fireAllRules

    val results = session.getQueryResults("SimpleResults")

    val res: util.Map[String, AnyRef] = results.toList.get(0)

    val r: util.ArrayList[Recommendation] = res.get("$result").asInstanceOf[java.util.ArrayList[Recommendation]]
    val recipe = r.get(0)
    println("========== " + recipe.getRecipeId)
    // TODO: get results with query
    Nil
  }

}
