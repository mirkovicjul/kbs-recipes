package database

import models.MeasurementScala
import scalikejdbc._

trait MeasurementRepo {

  def allMeasurements(): Seq[MeasurementScala]

}

class MeasurementRepoImpl extends MeasurementRepo {

  val ms = MeasurementScala.syntax

  override def allMeasurements(): Seq[MeasurementScala] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(MeasurementScala.as(ms))
      }.map(result => MeasurementScala(result, ms.resultName)).list().apply()
    }

}
