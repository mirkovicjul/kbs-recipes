package forms

import play.api.libs.json.{Json, OFormat}

case class RecipeForm(
    title: String,
    description: String,
    numberOfPortions: Int,
    vegan: Boolean,
    vegetarian: Boolean,
    junkFood: Boolean,
    daysBeforeExpiration: Int,
    preparationTime: Int,
    ingredients: Seq[RecipeIngredientForm],
    image: Option[String]
)

case class RecipeIngredientForm(
    id: Long,
    quantity: BigDecimal,
    measurement: String
)

object RecipeForm {
  implicit val recipeFormFormat: OFormat[RecipeForm] = Json.format[RecipeForm]
  implicit val recipeIngredientFormFormat: OFormat[RecipeIngredientForm] = Json.format[RecipeIngredientForm]
}
