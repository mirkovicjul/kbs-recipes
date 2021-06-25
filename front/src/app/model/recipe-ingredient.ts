import { Ingredient } from "./ingredient";
import { Measurement } from "./measurement";

export interface RecipeIngredient {
    id: number;
    ingredients: Ingredient[];
    measurement: Measurement;
    quantity: number
}