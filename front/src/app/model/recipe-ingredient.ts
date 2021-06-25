import { Ingredient } from "./ingredient";
import { Measurement } from "./measurement";

export interface RecipeIngredient {
    id: number;
    ingredient: Ingredient;
    measurement: Measurement;
    quantity: number
}