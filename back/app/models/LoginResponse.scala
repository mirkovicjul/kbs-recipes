package models

case class LoginResponse(
  success: Boolean,
  token: String,
  message: String
)

