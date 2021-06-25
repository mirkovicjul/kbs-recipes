import { Ingredient } from "./ingredient";
import { Measurement } from "./measurement";

export interface IngredientStorageItem {
    id: number;
    ingredient: Ingredient;
    measurement: Measurement;
    quantity: number;
    bestBefore: Date;
}