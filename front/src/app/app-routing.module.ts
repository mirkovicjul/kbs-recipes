import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { RecommendComponent } from './components/recommend/recommend.component';
import { StorageComponent } from './components/storage/storage.component';
import { HomeComponent } from './components/home/home.component';
import { IngredientComponent } from './components/ingredient/ingredient.component';
import { HistoryComponent } from './components/history/history.component';

const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'login', component: LoginComponent},
  { path: 'recipe', component: RecipeComponent},
  { path: 'ingredient', component: IngredientComponent},
  { path: 'recommend', component: RecommendComponent},
  { path: 'storage', component: StorageComponent},
  { path: 'home', component: HomeComponent},
  { path: 'history', component: HistoryComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
