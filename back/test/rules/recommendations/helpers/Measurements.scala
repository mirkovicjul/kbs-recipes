package rules.recommendations.helpers

import drools.recommendation.Measurement

trait Measurements {

  val grams: Measurement = new Measurement("grams", 1)

  val milliliter: Measurement = new Measurement("grams", 1)

}
