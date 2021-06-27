import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { HomepageRecommendation } from '../model/homepage-recommendation';
import { Recipe } from '../model/recipe';


@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  private apiUrl: String = environment.apiUrl;

  constructor(private http: HttpClient) { }

  homepage() {
    var config = {
      headers: {
        'authorization': localStorage.getItem("id_token")
      }
    }    
 
  
    return this.http.post<Recipe>(`${this.apiUrl}recommendation/regular`, "", config) ;
  }
  
}
