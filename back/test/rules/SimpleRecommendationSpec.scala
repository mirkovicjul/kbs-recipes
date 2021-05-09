package rules

import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import services.RecommendationService

final class SimpleRecommendationSpec
    extends WordSpec
    with GuiceOneAppPerSuite
    with MustMatchers {

  val recommendationService: RecommendationService =
    app.injector.instanceOf[RecommendationService]

  "SimpleRecommendationSpec" when {
    "recommending one recipe" should {
      "return vegan meal" in {
        // given

        // when
        val result = recommendationService.recommend(2)

        // then
        result mustBe Nil
      }
    }
  }

}
