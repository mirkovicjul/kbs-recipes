import { Recipe } from "./recipe";

export interface MealStorageItem {
    id: number;
    recipe: Recipe;
    servings: number;
    bestBefore: Date;
}