package models

final case class RecipeIngredient(
    ingredientId: Long,
    recipeId: Long,
    measurementId: Long,
    amount: Double
)
