package controllers

import com.typesafe.scalalogging.LazyLogging
import controllers.FileUploadController._
import play.api.libs.json.{Json, OFormat, OWrites}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.ExecutionContext

class FileUploadController @Inject()(
    val controllerComponents: ControllerComponents
)(implicit ec: ExecutionContext)
    extends BaseController
    with LazyLogging {

  private val Sep = java.io.File.separator
  private val Host = "http://localhost:9000/images/"

  def upload(): Action[AnyContent] = Action.apply { implicit req =>
    req.body.asMultipartFormData.flatMap {
      _.file("image").map { picture =>
        picture.ref
          .moveTo(
            new java.io.File(s"public${Sep}content$Sep${UUID.randomUUID}"))
          .getFileName
          .toString
      }
    }.map(f => s"$Host$f") match {
      case Some(filePath) =>
        Ok(Json.toJson(UploadFileResponse(filePath)))
      case None =>
        BadRequest
    }
  }

}

object FileUploadController {
  case class UploadFileResponse(filePath: String)
  implicit val uploadFileResponseW: OFormat[UploadFileResponse] =
    Json.format[UploadFileResponse]
}
