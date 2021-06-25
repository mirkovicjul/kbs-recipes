import { Component, OnInit } from '@angular/core';
import { IngredientStorageItem } from 'src/app/model/ingredient-storage-item';
import { MealStorageItem } from 'src/app/model/meal-storage-item';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-storage',
  templateUrl: './storage.component.html',
  styleUrls: ['./storage.component.css']
})
export class StorageComponent implements OnInit {

  ingredientStorage: IngredientStorageItem[]

  mealStorage: MealStorageItem[]

  constructor(private storageService: StorageService) { }

  ngOnInit() {
    this.storageService.getIngredientStorage().subscribe(response => this.ingredientStorage = response);
    this.storageService.getRecipeStorage().subscribe(response => this.mealStorage = response);
  }

}
