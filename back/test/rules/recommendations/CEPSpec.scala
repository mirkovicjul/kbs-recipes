package rules.recommendations

import drools.SessionCache
import drools.cep.WantsNewRecommendation
import drools.recommendation.Recommendation
import org.kie.api.runtime.KieSession
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import services.RecommendationService

class CEPSpec extends WordSpec with GuiceOneAppPerSuite with MustMatchers {

  val recommendationService: RecommendationService =
    app.injector.instanceOf[RecommendationService]
  val sessionCache: SessionCache = app.injector.instanceOf[SessionCache]

  "CEP spec" when {
    "recommending one recipe" should {
      "return nothing due to no info about given user" in {
        // given
        val userId = 1
        val session: KieSession = sessionCache.simpleSession(1)

        (0 until 10).foreach { s =>
          Thread.sleep(1000)
          session
            .getEntryPoint("recommendation frequency")
            .insert(new WantsNewRecommendation())
        }

        // when

        // then

      }

    }
  }
}
