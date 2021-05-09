package services

import com.github.benmanes.caffeine.cache.{Cache, Caffeine}
import com.typesafe.scalalogging.LazyLogging
import drools.recommendation.{Recipe, Recommendation}
import org.kie.api.runtime.{KieContainer, KieSession}

import java.util.concurrent.TimeUnit
import javax.inject.Inject

final case class Ingredient(name: String)

trait RecommendationService {

  def recommend(userId: Long): List[Recommendation]

}

class RecommendationServiceImpl @Inject()(
    kContainer: KieContainer
) extends RecommendationService
    with LazyLogging {

  private val sessionCache: Cache[Long, KieSession] =
    Caffeine
      .newBuilder
      .expireAfterWrite(2, TimeUnit.DAYS)
      .maximumSize(10_000).build[Long, KieSession]

  private def simpleSession(userId: Long): KieSession =
    sessionCache.get(userId, _ => kContainer.newKieSession("SimpleRecommendation"))

  override def recommend(userId: Long): List[Recommendation] = {
    val ksession: KieSession = simpleSession(userId)

    ksession.insert(new Recipe(1, "salt"))
    ksession.insert(new Recipe(2, "pepper"))

    ksession.fireAllRules

    ksession.dispose()
    Nil
  }

}
