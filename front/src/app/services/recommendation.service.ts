import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { HomepageRecommendation } from '../model/homepage-recommendation';


@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  private apiUrl: String = environment.apiUrl;

  constructor(private http: HttpClient) { }

  homepage(): Array<HomepageRecommendation> {
    let recommendations: Array<HomepageRecommendation> = new Array();

    for(let i = 1; i < 20; i++) {
      recommendations.push({
        name: `Hummus${i}`,
        thumbnail: "https://i.pinimg.com/474x/0b/75/9e/0b759e6922637ff308b7fe9fb844326e.jpg"
      });
    }

    return recommendations;
  }
  
}
