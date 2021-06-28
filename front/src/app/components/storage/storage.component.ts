import { Component, OnInit } from '@angular/core';
import { IngredientStorageItem } from 'src/app/model/ingredient-storage-item';
import { MealStorageItem } from 'src/app/model/meal-storage-item';
import { StorageService } from 'src/app/services/storage.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Ingredient } from 'src/app/model/ingredient';
import { IngredientService } from 'src/app/services/ingredient.service';

@Component({
  selector: 'app-storage',
  templateUrl: './storage.component.html',
  styleUrls: ['./storage.component.css']
})
export class StorageComponent implements OnInit {

  ingredientStorage: IngredientStorageItem[]

  mealStorage: MealStorageItem[];
  ingredientToUseQuantity: number;
  mealToUseServings: number;
  closeResult = '';
  allIngredients: Ingredient[]
  measurements: String[] = ["grams", "millilitre", "teaspoon", "tablespoon"]
  
  constructor(private storageService: StorageService, private ingredientService: IngredientService, private modalService: NgbModal) { }

  ngOnInit() {
    this.storageService.getIngredientStorage().subscribe(response => this.ingredientStorage = response);
    this.storageService.getRecipeStorage().subscribe(response => this.mealStorage = response);
    this.ingredientService.getAllIngredients().subscribe(response => this.allIngredients = response);
  }

  open(content) {
   // this.storageService.getIngredientsForRecipe(this.id).subscribe(i => this.ingredientsInStorage = i);
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
