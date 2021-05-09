package services

import com.typesafe.scalalogging.LazyLogging
import drools.recommendation.{Recipe, Recommendation}
import org.kie.api.runtime.{KieContainer, KieSession}

import javax.inject.Inject

final case class Ingredient(name: String)

trait RecommendationService {

  def recommend(userId: Long): List[Recommendation]

}

class RecommendationServiceImpl @Inject()(
    kContainer: KieContainer
) extends RecommendationService
    with LazyLogging {

  override def recommend(userId: Long): List[Recommendation] = {
    val ksession: KieSession = kContainer.newKieSession("SimpleRecommendation")

    ksession.insert(new Recipe(1, "salt"))
    ksession.insert(new Recipe(2, "pepper"))

    ksession.fireAllRules
    logger.debug("After")

    ksession.dispose()
    Nil
  }

}
