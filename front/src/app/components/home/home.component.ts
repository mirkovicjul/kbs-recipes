import { Component, OnInit, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { RecommendationService } from 'src/app/services/recommendation.service';
import { HomepageRecommendation } from 'src/app/model/homepage-recommendation';
import { range } from 'rxjs';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  recommendation: Array<Array<HomepageRecommendation>>;

  constructor(private router: Router, private userService: LoginService, private recommendationService: RecommendationService) { 
    if (!this.userService.isLoggedIn()) {
      this.router.navigateByUrl('/login');
    }
  }

  getHomepageRecommendations(): Array<Array<HomepageRecommendation>>{
    let recs: Array<HomepageRecommendation> = this.recommendationService.homepage();
    
    let formated: Array<Array<HomepageRecommendation>> = new Array();
    Array.from(Array( Math.floor(recs.length / 4)).keys())
    .map((v, i, a) => formated.push(recs.slice(i * 4, i * 4 + 4)));

    return formated;
  }

  ngOnInit() {
    this.recommendation = this.getHomepageRecommendations();
    console.log("load recommendations")
  }

}
