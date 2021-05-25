package rules.recommendations

import com.google.common.collect.ImmutableMap
import drools.SessionCache
import drools.recommendation.{Ingredient, IngredientType, Measurement, Quantity, Recipe, Recommendation}
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import services.RecommendationService

import scala.jdk.CollectionConverters.IteratorHasAsScala

class DietSpec extends WordSpec
  with GuiceOneAppPerSuite
  with MustMatchers {

  val recommendationService: RecommendationService =
    app.injector.instanceOf[RecommendationService]
  val sessionCache: SessionCache = app.injector.instanceOf[SessionCache]

  "DietSpec" when {
    "marking vegan or vegetarian recipes" should {
      "successfully mark vegetarian recipes" in {
        // given
        val session = sessionCache.simpleSession(1)
        val grams = new Measurement("grams", 1)

        val rice    = new Ingredient(1, "rice")
        rice.setType(IngredientType.Grains)
        val anchovy = new Ingredient(2, "anchovy")
        anchovy.setType(IngredientType.Fish)
        val chicken = new Ingredient(3, "chicken")
        chicken.setType(IngredientType.Meat)
        val tomato  = new Ingredient(4, "tomato")
        tomato.setType(IngredientType.Vegetable)
        val pork    = new Ingredient(5, "pork")
        pork.setType(IngredientType.Meat)

        val chickenAnchovy = new Recipe(
          1,
          "Chicken with rice and anchovy",
          ImmutableMap.of(chicken,
            new Quantity(500, grams),
            rice,
            new Quantity(500, grams),
            anchovy,
            new Quantity(200, grams))
        )

        val riceTomato = new Recipe(
          1,
          "Chicken with rice and anchovy",
          ImmutableMap.of(rice,
            new Quantity(500, grams),
            tomato,
            new Quantity(200, grams))
        )

        Seq(rice, chicken, anchovy, tomato).foreach(session.insert)
        Seq(chickenAnchovy, riceTomato).foreach(session.insert)

        //when
        session.getAgenda.getAgendaGroup("Diet").setFocus();
        session.fireAllRules();

        //then
        session
          .getQueryResults("Recipes")
          .iterator()
          .asScala
          .map(r => r.get("$recipe").asInstanceOf[Recipe])
          .toSeq.count(_.getVegetarian==true) mustBe 1

      }
    }
  }
}
