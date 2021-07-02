package controllers

import com.typesafe.scalalogging.LazyLogging
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.libs.Json
import services.MeasurementService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class MeasurementController @Inject()(
                                       val controllerComponents: ControllerComponents,
                                       measurementService: MeasurementService
                                     )(implicit ec: ExecutionContext)
  extends BaseController with LazyLogging {

  def getAllMeasurements() = Action.async {

    val measurements = measurementService.allMeasurements()
    Future(Ok(Json.toJson(measurements).toString))
  }

}
