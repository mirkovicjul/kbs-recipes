import { Component, OnInit, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { RecommendationService } from 'src/app/services/recommendation.service';
import { HomepageRecommendation } from 'src/app/model/homepage-recommendation';
import { range } from 'rxjs';
import { Recipe } from 'src/app/model/recipe';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  recommendation: Recipe;
  
  constructor(private router: Router, private userService: LoginService, private recommendationService: RecommendationService) { 
    if (!this.userService.isLoggedIn()) {
      this.router.navigateByUrl('/login');
    }
  }

  getHomepageRecommendations() {
    this.recommendationService.homepage().subscribe(r => this.recommendation = r);
  }

  ngOnInit() {
    console.log("load recommendations")
    this.getHomepageRecommendations();
  }

}
