package rules.recommendations

import com.google.common.collect.ImmutableList
import drools.SessionCache
import drools.conclusion.EatingUnhealthy
import drools.recommendation.{Ingredient, MadeRecipe, Recipe, StorageItem, User}
import org.kie.api.runtime.KieSession
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import rules.recommendations.helpers.Recipes
import services.RecommendationService

import java.time.LocalDate
import scala.jdk.CollectionConverters.IteratorHasAsScala

class HistoryRecommendationSpec extends WordSpec with GuiceOneAppPerSuite with MustMatchers {
  val recommendationService: RecommendationService = app.injector.instanceOf[RecommendationService]
  val sessionCache: SessionCache = app.injector.instanceOf[SessionCache]

  "HistoryRecommendation" when {
    "checking if user is eating unhealthy" should {
      "successfully mark that user is eating unhealthy" in new Fixture {
        //given
        Seq(rice, chicken, anchovy, tomato).foreach(session().insert)
        frenchFries.setJunkFood(true)
        Seq(chickenAnchovy, riceTomato, frenchFries).foreach(session().insert)

        val mr = new MadeRecipe(frenchFries, LocalDate.now().minusDays(7), 1)
        val mr2 = new MadeRecipe(frenchFries, LocalDate.now().minusDays(6), 1)
        val mr3 = new MadeRecipe(frenchFries, LocalDate.now().minusDays(5), 1)
        val mr4 = new MadeRecipe(riceTomato, LocalDate.now().minusDays(4), 1)
        val mr5 = new MadeRecipe(frenchFries, LocalDate.now().minusDays(11), 1)
        Seq(mr, mr2, mr3, mr4, mr5).foreach(session().getEntryPoint("history").insert)

        session().insert(user)

        //when
        session().getAgenda().getAgendaGroup("Conclusions").setFocus()
        session().fireAllRules()

        //then
        val result: Option[EatingUnhealthy] = session()
          .getQueryResults("Conclusions")
          .iterator()
          .asScala
          .map(r => r.get("$unhealthy").asInstanceOf[EatingUnhealthy])
          .toSeq
          .headOption
        result.isDefined.mustBe(true)
      }
      "unsuccessfully mark that user is eating unhealthy" in new Fixture {
        //given
        Seq(rice, chicken, anchovy, tomato).foreach(session(2).insert)
        frenchFries.setJunkFood(true)
        Seq(chickenAnchovy, riceTomato, frenchFries).foreach(session(2).insert)

        val mr = new MadeRecipe(frenchFries, LocalDate.now().minusDays(15), 1)
        val mr2 = new MadeRecipe(frenchFries, LocalDate.now().minusDays(6), 1)
        val mr3 = new MadeRecipe(frenchFries, LocalDate.now().minusDays(5), 1)
        val mr4 = new MadeRecipe(riceTomato, LocalDate.now().minusDays(4), 1)
        val mr5 = new MadeRecipe(frenchFries, LocalDate.now().minusDays(11), 1)
        Seq(mr, mr2, mr3, mr4, mr5).foreach(session(2).getEntryPoint("history").insert)

        session(2).insert(user)

        //when
        session(2).getAgenda().getAgendaGroup("Conclusions").setFocus()
        session(2).fireAllRules()

        //then
        val result: Option[EatingUnhealthy] = session(2)
          .getQueryResults("Conclusions")
          .iterator()
          .asScala
          .map(r => r.get("$unhealthy").asInstanceOf[EatingUnhealthy])
          .toSeq
          .headOption
        result.isDefined.mustBe(false)
      }
    }
  }

  trait Fixture extends Recipes {
    def session(id: Long=1): KieSession = sessionCache.simpleSession(id)
    val user = new User(
      1,
      ImmutableList.of(),
      ImmutableList.of(),
      ImmutableList.of(),
      ImmutableList.of(),
      ImmutableList.of()
    )
  }

}
