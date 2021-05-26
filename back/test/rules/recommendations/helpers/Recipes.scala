package rules.recommendations.helpers

import com.google.common.collect.ImmutableList
import drools.recommendation.{Recipe, RecipeIngredient}

trait Recipes extends Ingredients with Measurements {

  val chickenAnchovy: Recipe = new Recipe(
    1,
    "Chicken with rice and anchovy",
    ImmutableList.of(
      new RecipeIngredient(chicken, 500, grams),
      new RecipeIngredient(rice, 500, grams),
      new RecipeIngredient(anchovy, 200, grams)
    )
  )

  val riceTomato: Recipe = new Recipe(
    2,
    "Chicken with rice and anchovy",
    ImmutableList.of(
      new RecipeIngredient(rice, 500, grams),
      new RecipeIngredient(tomato, 200, grams)
    )
  )

  val frenchFries: Recipe = new Recipe(
    3,
    "French fries",
    ImmutableList.of(
      new RecipeIngredient(potato, 200, grams),
      new RecipeIngredient(sunflowerOil, 200, milliliter)
    )
  )

  val allRecipes: Seq[Recipe] = Seq(
    chickenAnchovy,
    riceTomato,
    frenchFries
  )

}
