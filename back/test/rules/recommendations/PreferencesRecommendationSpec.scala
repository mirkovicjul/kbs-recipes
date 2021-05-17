package rules.recommendations

import drools.SessionCache
import drools.recommendation.{Ingredient, Recipe, Recommendation, User}
import org.scalatest.{MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import services.RecommendationService

import java.util.{List => JList}
import scala.collection.JavaConverters.seqAsJavaListConverter


final class PreferencesRecommendationSpec
    extends WordSpec
    with GuiceOneAppPerSuite
    with MustMatchers {

  val recommendationService: RecommendationService =
    app.injector.instanceOf[RecommendationService]

  val sessionCache: SessionCache = app.injector.instanceOf[SessionCache]

  "PreferencesRecommendationSpec" when {
    "recommend recipes based on user preferences" should {
      "successfully return recipes recommended by user liked and allergic lists preferences" in {
        // given
        val userId  = 1
        val session = sessionCache.simpleSession(userId)

        val rice =  new Ingredient(1, "rice");
        val anchovy =  new Ingredient(2, "anchovy");
        val chicken = new Ingredient(3, "chicken");
        val tomato = new Ingredient(4, "tomato");

        val liked: JList[Ingredient] = Seq(rice, chicken).asJava

        val ingr = Seq(rice, anchovy, chicken).asJava
        val ingr2 = Seq(chicken).asJava

        val allergies = Seq(rice).asJava

        val usr = new User(userId, liked, allergies)

        val recipe = new Recipe(4, "Chicken with anchovy", ingr)
        val recipe2 = new Recipe(3, "Chicken", ingr2)

        Seq(rice, anchovy, chicken , tomato).foreach(session.insert)
        session.insert(usr)
        Seq(recipe, recipe2).foreach(session.insert)

        // when
        val result: Seq[Recommendation] = recommendationService.recommend(userId)

        // then
        result.map(_.getRecipeId) mustBe Seq(recipe2.getId)
      }
    }
  }
}
