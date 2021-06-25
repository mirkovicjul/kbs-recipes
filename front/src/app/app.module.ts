import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatNativeDateModule} from '@angular/material/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { StorageComponent } from './components/storage/storage.component';
import { IngredientComponent } from './components/ingredient/ingredient.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { RecommendComponent } from './components/recommend/recommend.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginService } from './services/login.service';
import { HomeComponent } from './components/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MaterialModule } from './material.module';
import { HistoryComponent } from './components/history/history.component';
import { HistoryService } from './services/history.service';
import { RecommendationService } from './services/recommendation.service';
import { RegistrationService } from './services/registration.service';
import { StorageService } from './services/storage.service';
import { RecipesComponent } from './components/recipes/recipes.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StorageComponent,
    IngredientComponent,
    RecipeComponent,
    RecommendComponent,
    NavbarComponent,
    HomeComponent,
    HistoryComponent,
    RecipesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatNativeDateModule
  ],
  providers: [
    LoginService,
    HistoryService,
    RecommendationService,
    RegistrationService,
    StorageService
  ],
  bootstrap: [
    AppComponent,
    RecipeComponent
  ],
  entryComponents: [RecipeComponent]
})
export class AppModule { }
