package rules.recommendations

import com.google.common.collect.ImmutableMap
import drools.SessionCache
import drools.recommendation._
import org.kie.api.runtime.KieSession
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import rules.recommendations.helpers.Recipes
import services.RecommendationService

import java.time.LocalDate
import java.util
import scala.jdk.CollectionConverters.IteratorHasAsScala

class StorageRecommendationSpec
    extends WordSpec
    with GuiceOneAppPerSuite
    with MustMatchers {

  val recommendationService: RecommendationService =
    app.injector.instanceOf[RecommendationService]
  val sessionCache: SessionCache = app.injector.instanceOf[SessionCache]

  "StorageRecommendationSpec" when {
    "recommending recipes" should {
//      "return recipes sorted by storage ingredient expiration date" in new Fixture {
//        // given
//        val user = new User(
//          1,
//          new util.ArrayList[Ingredient](),
//          new util.ArrayList[Ingredient](),
//          new util.ArrayList[Ingredient](),
//          new util.ArrayList[Ingredient](),
//          util.Arrays.asList(
//            new StorageItem(anchovy,
//                            new Quantity(100, grams),
//                            LocalDate.now().plusDays(2)),
//            new StorageItem(chicken,
//                            new Quantity(600, grams),
//                            LocalDate.now().plusDays(17)),
//            new StorageItem(pork,
//                            new Quantity(600, grams),
//                            LocalDate.now().minusDays(8))
//          )
//        )
//
//        val chickenAnchovy = new Recipe(
//          1,
//          "Chicken with rice and anchovy",
//          ImmutableMap.of(chicken,
//                          new Quantity(500, grams),
//                          rice,
//                          new Quantity(500, grams),
//                          anchovy,
//                          new Quantity(200, grams))
//        )
//
//        val chickenTomato = new Recipe(
//          2,
//          "Chicken with tomato",
//          ImmutableMap.of(chicken,
//                          new Quantity(300, grams),
//                          tomato,
//                          new Quantity(200, grams))
//        )
//
//        val riceTomato = new Recipe(
//          3,
//          "Rice and tomato",
//          ImmutableMap.of(rice,
//                          new Quantity(200, grams),
//                          tomato,
//                          new Quantity(200, grams))
//        )
//
//        val session = sessionCache.simpleSession(user.getId)
//        Seq(rice, chicken, anchovy, tomato).foreach(session.insert)
//        Seq(chickenAnchovy, chickenTomato, riceTomato).foreach(session.insert)
//        session.insert(user)
//
//        // when
//        val result: Seq[Recommendation] =
//          recommendationService.recommend(user.getId)
//
//        // then
//          result.map(_.getRecipeId) mustBe Seq(chickenAnchovy.getId)
//
//
//      }
    }

  }

  trait Fixture extends Recipes {
    val session: KieSession = sessionCache.simpleSession(1)
  }

}
