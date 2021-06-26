package forms

case class RecipeForm(
    title: String,
    description: String,
    numberOfPortions: Int,
    vegan: Boolean,
    vegetarian: Boolean,
    junkFood: Boolean,
    daysBeforeExpiration: Int,
    preparationTime: Int
)
