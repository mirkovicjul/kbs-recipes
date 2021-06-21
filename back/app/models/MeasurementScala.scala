package models

import scalikejdbc._

case class MeasurementScala(
    id: Long,
    measurement: String,
    proportion: Double
)

object MeasurementScala extends SQLSyntaxSupport[MeasurementScala] {

  override def tableName: String = "measurements"

  def apply(rs: WrappedResultSet, rn: ResultName[MeasurementScala]): MeasurementScala =
    MeasurementScala(
      id = rs.get[Long](rn.id),
      measurement = rs.get[String](rn.measurement),
      proportion = rs.get[Double](rn.proportion)
    )

}
