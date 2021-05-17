package rules.recommendations

import drools.SessionCache
import drools.recommendation.{Recipe, Recommendation}
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import services.RecommendationService

final class SimpleRecommendationSpec
    extends WordSpec
    with GuiceOneAppPerSuite
    with MustMatchers {

  val recommendationService: RecommendationService = app.injector.instanceOf[RecommendationService]
  val sessionCache: SessionCache = app.injector.instanceOf[SessionCache]

  "SimpleRecommendationSpec" when {
    "recommending one recipe" should {
      "return nothing due to no info about given user" in {
        // given
        val userId = 1

        // when
        val result: Seq[Recommendation] = recommendationService.recommend(userId)

        // then
        result mustBe Nil
      }

      "return vegan meal" in {
        // given
        val userId = 2
        val session = sessionCache.simpleSession(userId)

        session.insert(new Recipe(1, "salt"))
        session.insert(new Recipe(2, "pepper"))

        // when
        val result: Seq[Recommendation] = recommendationService.recommend(userId)

        // then
        result.size mustBe 1
        result.head.getHit mustBe 0.78
        result.head.getRecipeId mustBe 1
      }
    }
  }

}
