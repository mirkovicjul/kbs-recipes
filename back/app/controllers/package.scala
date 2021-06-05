import models.LoginResponse
import play.api.libs.json.{Json, OFormat}

package object controllers {

  implicit val loginResponseFormat: OFormat[LoginResponse] = Json.format[LoginResponse]


}
