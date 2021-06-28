import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Ingredient } from '../model/ingredient';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  apiUrl = environment.apiUrl

  constructor(private http: HttpClient) { }
  getAllIngredients() {
    var config = {
      headers: {
        'content-type': 'application/json',
        'accept': 'application/json'
      }
    }
    return this.http.get<Ingredient[]>(`${this.apiUrl}ingredients`, config);
  }
}
