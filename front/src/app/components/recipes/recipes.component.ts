import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/model/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  recipes: Recipe[]

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.recipeService.getAllRecipes().subscribe(response => this.recipes = response);
  }

}
