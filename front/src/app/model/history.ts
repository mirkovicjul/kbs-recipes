import { Recipe } from "./recipe";

export interface HistoryItem {
    recipe: Recipe;
    servings: number;
    date: Date;
}