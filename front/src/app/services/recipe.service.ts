import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Recipe } from '../model/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  apiUrl = environment.apiUrl

  constructor(private http: HttpClient) { }

  getAllRecipes() {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json'
      }
    }
    return this.http.get<Recipe[]>(`${this.apiUrl}recipes`, config);
  }

  getRecipeById(id: number) {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json'
      }
    }
    return this.http.get<Recipe>(`${this.apiUrl}recipe/`+id, config);
  }
  
}
