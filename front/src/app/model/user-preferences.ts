import { Ingredient } from "./ingredient";

export interface UserPreferences {
    likes: Ingredient[];
    dislikes: Ingredient[];
    allergies: Ingredient[];
    unavailable: Ingredient[];
}