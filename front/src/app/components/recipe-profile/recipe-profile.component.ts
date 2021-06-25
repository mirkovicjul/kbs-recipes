import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/model/recipe';

@Component({
  selector: 'app-recipe-profile',
  templateUrl: './recipe-profile.component.html',
  styleUrls: ['./recipe-profile.component.css']
})
export class RecipeProfileComponent implements OnInit {
  
  id: number;
  recipe: Recipe;

  constructor(private recipeService: RecipeService, private route: ActivatedRoute) { 
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
  }
  
  ngOnInit() {
    this.recipeService.getRecipeById(this.id).subscribe(response => this.recipe = response);
  }

}
