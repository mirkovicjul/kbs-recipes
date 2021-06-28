import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { HistoryItem } from '../model/history';
import { IngredientStorageItem } from '../model/ingredient-storage-item';
import { MealStorageItem } from '../model/meal-storage-item';


@Injectable({
  providedIn: 'root'
})
export class StorageService {

  apiUrl = environment.apiUrl

  constructor(private http: HttpClient) { }

  getIngredientStorage() {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.get<IngredientStorageItem[]>(`${this.apiUrl}ingredientStorage`, config);
  }

  getRecipeStorage() {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.get<MealStorageItem[]>(`${this.apiUrl}recipeStorage`, config);
  }

  getIngredientsForRecipe(recipeId: number) {
    console.log("======= uso u servis")
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json',
        'authorization': localStorage.getItem("id_token")
      }
    }
    return this.http.get<IngredientStorageItem[]>(`${this.apiUrl}ingredientStorage/`+recipeId, config);

  }
}
