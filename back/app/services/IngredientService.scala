package services

import com.typesafe.scalalogging.LazyLogging
import database.IngredientRepo
import models.{Ingredient}

import javax.inject.Inject

trait IngredientService {
  def allIngredients(): Seq[Ingredient]
}

class IngredientServiceImpl @Inject()(
                                   ingredientRepo: IngredientRepo
                                 ) extends IngredientService
  with LazyLogging {

  override def allIngredients(): Seq[Ingredient] = ingredientRepo.allIngredients()

}