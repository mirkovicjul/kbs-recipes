package rules.recommendations.helpers

import com.google.common.collect.ImmutableMap
import drools.recommendation.{Quantity, Recipe}

trait Recipes extends Ingredients with Measurements {

  val chickenAnchovy: Recipe = new Recipe(
    1,
    "Chicken with rice and anchovy",
    ImmutableMap.of(
      chicken,
      new Quantity(500, grams),
      rice,
      new Quantity(500, grams),
      anchovy,
      new Quantity(200, grams)
    )
  )

  val riceTomato: Recipe = new Recipe(
    2,
    "Chicken with rice and anchovy",
    ImmutableMap.of(
      rice,
      new Quantity(500, grams),
      tomato,
      new Quantity(200, grams)
    )
  )

  val frenchFries: Recipe = new Recipe(
    3,
    "French fries",
    ImmutableMap.of(
      potato,
      new Quantity(200, grams),
      sunflowerOil,
      new Quantity(200, milliliter)
    )
  )

  val allRecipes: Seq[Recipe] = Seq(
    chickenAnchovy,
    riceTomato,
    frenchFries
  )

}
