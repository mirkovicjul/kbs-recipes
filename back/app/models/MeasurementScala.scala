package models

import scalikejdbc._

case class Measurement(
    id: Long,
    measurement: String,
    proportion: Double
)

object Measurement extends SQLSyntaxSupport[Measurement] {

  override def tableName: String = "measurements"

  def apply(rs: WrappedResultSet, rn: ResultName[Measurement]): Measurement =
    Measurement(
      id = rs.get[Long](rn.id),
      measurement = rs.get[String](rn.measurement),
      proportion = rs.get[Double](rn.proportion)
    )

}
