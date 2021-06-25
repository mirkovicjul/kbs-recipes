import { RecipeIngredient } from "./recipe-ingredient";

export interface Recipe {
    id: number;
    title: string;
    description: string;
    numberOfPortions: number;
    ingredients: RecipeIngredient[];
    vegan: boolean;
    vegetarian: boolean;
    junkFood: boolean;
    daysBeforeExpiration: number
}