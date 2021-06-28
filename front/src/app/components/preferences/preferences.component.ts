import { Component, OnInit } from '@angular/core';
import { Ingredient } from 'src/app/model/ingredient';
import { UserPreferences } from 'src/app/model/user-preferences';
import { IngredientService } from 'src/app/services/ingredient.service';
import { PreferencesService } from 'src/app/services/preferences.service';

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrls: ['./preferences.component.css']
})
export class PreferencesComponent implements OnInit {

  ingredientsVisible: IngredientsVisible = ({likes: false, dislikes: false, allergies: false,  unavailable: false});

  constructor(private preferencesService: PreferencesService, private ingredientService: IngredientService) { }

  userPreferences: UserPreferences;
  allIngredients: Ingredient[];

  ngOnInit() {
    this.preferencesService.getUserPreferences().subscribe(u => this.userPreferences = u);
    this.ingredientService.getAllIngredients().subscribe(i => this.allIngredients = i);
  }

  showIngredients(id) {
    switch(id){
      case 1:
        this.ingredientsVisible.likes = !this.ingredientsVisible.likes;
        break;
      case 2:
        this.ingredientsVisible.dislikes = !this.ingredientsVisible.dislikes;
        break;
      case 3:
        this.ingredientsVisible.allergies = !this.ingredientsVisible.allergies;
        break;
      case 4:
        this.ingredientsVisible.unavailable = !this.ingredientsVisible.unavailable;
        break;
    }
  }

  addLike(id: number){
   var ingr  = this.allIngredients.filter(i => i.id == id)[0]
   var ingrInLikes = this.userPreferences.likes.filter(l => l.id == ingr.id)
   if(ingrInLikes.length==0)
      this.userPreferences.likes.push(ingr)
  }
  addDislike(id: number){
    var ingr  = this.allIngredients.filter(i => i.id == id)[0]
    var ingrInDislikes = this.userPreferences.dislikes.filter(l => l.id == ingr.id)
    if(ingrInDislikes.length==0)
      this.userPreferences.dislikes.push(ingr)
  }
  addAllergy(id: number){
    var ingr  = this.allIngredients.filter(i => i.id == id)[0]
    var ingrInAllergies = this.userPreferences.allergies.filter(l => l.id == ingr.id)
    if(ingrInAllergies.length==0)
      this.userPreferences.allergies.push(ingr)
  }
  addUnavailable(id: number){
    var ingr  = this.allIngredients.filter(i => i.id == id)[0]
    var ingrInUnavailable = this.userPreferences.unavailable.filter(l => l.id == ingr.id)
    if(ingrInUnavailable.length==0)
      this.userPreferences.unavailable.push(ingr)
  }

  removeLike(id: number){
    var ingr = this.userPreferences.likes.filter(i => i.id == id)[0]
    const index: number = this.userPreferences.likes.indexOf(ingr);
    if (index !== -1) {
        this.userPreferences.likes.splice(index, 1);
    }
  }

  removeDislike(id: number){
    var ingr = this.userPreferences.dislikes.filter(i => i.id == id)[0]
    const index: number = this.userPreferences.dislikes.indexOf(ingr);
    if (index !== -1) {
        this.userPreferences.dislikes.splice(index, 1);
    }
  }

  removeAllergy(id: number){
    var ingr = this.userPreferences.allergies.filter(i => i.id == id)[0]
    const index: number = this.userPreferences.allergies.indexOf(ingr);
    if (index !== -1) {
        this.userPreferences.allergies.splice(index, 1);
    }
  }

  removeUnavailable(id: number){
    var ingr = this.userPreferences.unavailable.filter(i => i.id == id)[0]
    const index: number = this.userPreferences.unavailable.indexOf(ingr);
    if (index !== -1) {
        this.userPreferences.unavailable.splice(index, 1);
    }
  }
 
}

interface IngredientsVisible {
    likes: Boolean;
    dislikes: Boolean;
    allergies: Boolean;
    unavailable: Boolean;
}
