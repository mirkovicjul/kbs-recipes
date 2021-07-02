package services

import com.typesafe.scalalogging.LazyLogging
import database.{IngredientRepo, MeasurementRepo}
import models.{Ingredient, MeasurementScala}

import javax.inject.Inject


trait MeasurementService {
  def allMeasurements(): Seq[MeasurementScala]
}

class MeasurementServiceImpl @Inject()(
                                       measurementRepo: MeasurementRepo
                                     ) extends MeasurementService
  with LazyLogging {

  override def allMeasurements(): Seq[MeasurementScala] = measurementRepo.allMeasurements()

}
