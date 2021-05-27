package database

import models.Measurement
import scalikejdbc._

trait MeasurementRepo {

  def allMeasurements(): Seq[Measurement]

}

class MeasurementRepoImpl extends MeasurementRepo {

  val ms = Measurement.syntax

  override def allMeasurements(): Seq[Measurement] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(Measurement.as(ms))
      }.map(result => Measurement(result, ms.resultName)).list().apply()
    }

}
