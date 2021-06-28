import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/model/recipe';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { IngredientStorageItem } from 'src/app/model/ingredient-storage-item';
import { StorageService } from 'src/app/services/storage.service';


@Component({
  selector: 'app-recipe-profile',
  templateUrl: './recipe-profile.component.html',
  styleUrls: ['./recipe-profile.component.css']
})
export class RecipeProfileComponent implements OnInit {
  
  id: number;
  recipe: Recipe;
  closeResult = '';
  ingredientsInStorage: IngredientStorageItem[];
  ingredientToUse: number;
  ingredientToUseQuantity: number;

  constructor(private recipeService: RecipeService, private storageService: StorageService, private route: ActivatedRoute, private modalService: NgbModal) { 
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
  }
  
  ngOnInit() {
    this.recipeService.getRecipeById(this.id).subscribe(response => this.recipe = response);
  }

  open(content) {
    this.storageService.getIngredientsForRecipe(this.id).subscribe(i => this.ingredientsInStorage = i);
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
