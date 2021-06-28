import { Component, OnInit } from '@angular/core';
import { Ingredient } from 'src/app/model/ingredient';
import { IngredientService } from 'src/app/services/ingredient.service';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  allIngredients: Ingredient[];
  measurements: String[] = ["grams", "millilitre", "teaspoon", "tablespoon"];
  ingredientsVisible: Boolean = false;

  constructor(private ingredientService: IngredientService) { }

  ngOnInit() {
    this.ingredientService.getAllIngredients().subscribe(response => this.allIngredients = response);
  }

  showIngredients(){
    this.ingredientsVisible = !this.ingredientsVisible;
  }
}
