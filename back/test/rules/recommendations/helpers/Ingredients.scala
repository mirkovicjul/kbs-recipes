package rules.recommendations.helpers

import drools.recommendation.{Ingredient, IngredientType}

trait Ingredients {

  val rice: Ingredient = new Ingredient(
    1,
    "rice",
    IngredientType.Grains,
    0,
    0,
    0
  )

  val anchovy: Ingredient = new Ingredient(
    2,
    "anchovy",
    IngredientType.Fish,
    0,
    0,
    0
  )

  val chicken: Ingredient = new Ingredient(
    3,
    "chicken",
    IngredientType.Meat,
    0,
    0,
    0
  )

  val tomato: Ingredient = new Ingredient(
    4,
    "tomato",
    IngredientType.Vegetable,
    0,
    0,
    0
  )

  val pork: Ingredient = new Ingredient(
    5,
    "pork",
    IngredientType.Meat,
    0,
    0,
    0
  )

  val potato: Ingredient = new Ingredient(
    6,
    "potato",
    IngredientType.Vegetable,
    0.1,
    20.1,
    1.9
  )

  val sunflowerOil: Ingredient = new Ingredient(
    7,
    "Sunflower Oil",
    IngredientType.Oils,
    100.0,
    0.0,
    0.0
  )

  val allIngredients: Seq[Ingredient] = Seq(
    rice,
    anchovy,
    chicken,
    tomato,
    pork,
    potato,
    sunflowerOil
  )

}
