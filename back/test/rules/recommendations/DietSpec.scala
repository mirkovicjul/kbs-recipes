package rules.recommendations

import drools.SessionCache
import drools.recommendation.Recipe
import org.kie.api.runtime.KieSession
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import rules.recommendations.helpers.Recipes
import services.RecommendationService

import scala.jdk.CollectionConverters.IteratorHasAsScala

class DietSpec extends WordSpec with GuiceOneAppPerSuite with MustMatchers {

  val recommendationService: RecommendationService =
    app.injector.instanceOf[RecommendationService]
  val sessionCache: SessionCache = app.injector.instanceOf[SessionCache]

  "DietSpec" when {
    "marking vegan or vegetarian recipes" should {
      "successfully mark vegetarian recipes" in new Fixture {
        // given
        Seq(rice, chicken, anchovy, tomato).foreach(session.insert)
        Seq(chickenAnchovy, riceTomato).foreach(session.insert)

        //when
        session.getAgenda.getAgendaGroup("Diet").setFocus()
        session.fireAllRules()

        //then
        session
          .getQueryResults("Recipes")
          .iterator()
          .asScala
          .map(r => r.get("$recipe").asInstanceOf[Recipe])
          .toSeq
          .count(_.getVegetarian == true) mustBe 1

      }

      "successfully mark junk food" in new Fixture {
        // given
        allIngredients.foreach(session.insert)
        allRecipes.foreach(session.insert)

        // when
        session.getAgenda.getAgendaGroup("Diet").setFocus()
        session.fireAllRules()
        val result: Seq[Recipe] = session
          .getQueryResults("Recipes")
          .iterator()
          .asScala
          .map(r => r.get("$recipe").asInstanceOf[Recipe])
          .toSeq

        // then
        result.exists(_.getId == frenchFries.getId) mustBe true
      }

    }
  }

  trait Fixture extends Recipes {
    val session: KieSession = sessionCache.simpleSession(1)
  }
}
