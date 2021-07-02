package database

import io.ebean.Finder
import models.{Ingredient, Measurement, MeasurementScala}
import scalikejdbc._

trait MeasurementRepo {

  def allMeasurements(): Seq[MeasurementScala]

  def one(id: Long): Option[Measurement]
}

class MeasurementRepoImpl extends MeasurementRepo {

  val ms = MeasurementScala.syntax

  private val find: Finder[Long, Measurement] =
    new Finder[Long, Measurement](classOf[Measurement])

  override def allMeasurements(): Seq[MeasurementScala] =
    DB readOnly { implicit session =>
      withSQL {
        select.from(MeasurementScala.as(ms))
      }.map(result => MeasurementScala(result, ms.resultName)).list().apply()
    }

  override def one(id: Long): Option[Measurement] ={
    Option(find.byId(id))
  }
}
