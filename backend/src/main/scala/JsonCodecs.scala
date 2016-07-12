import Model.{AppEvent, JwtPayload, _}
import io.circe.{Decoder, Encoder, Json, JsonObject}
import io.circe._
import scala.util.Try
import io.circe.Decoder
import io.circe.jawn._

import io.circe.generic.auto._
import io.circe.syntax._

import scala.util.Try

object JsonCodecs {

  ////////// https://github.com/travisbrown/circe/pull/325/files /////////////////////////////////
  def enumDecoder[E <: Enumeration](enum: E): Decoder[E#Value] =
    Decoder.decodeString.flatMap { str =>
      Decoder.instanceTry { _ =>
        Try(enum.withName(str))
      }
    }

  def enumEncoder[E <: Enumeration](enum: E): Encoder[E#Value] = new Encoder[E#Value] {
    override def apply(e: E#Value): Json = Encoder.encodeString(e.toString)
  }
  /////////////////////////////////////////////////////////////////////////////////////////////////

  implicit val appActionDecoder = enumDecoder(AppAction)
  implicit val appActionEncoder = enumEncoder(AppAction)

  implicit val appEventTypeDecoder = enumDecoder(AppEventType)
  implicit val appEventTypeEncoder = enumEncoder(AppEventType)

  implicit val appSectionDecoder = enumDecoder(AppSection)
  implicit val appSectionEncoder = enumEncoder(AppSection)

  implicit val appEventSeverityDecoder = enumDecoder(AppEventSeverity)
  implicit val appEventSeverityEncoder = enumEncoder(AppEventSeverity)

  implicit val appActionResultDecoder = enumDecoder(AppActionResult)
  implicit val appActionResultEncoder = enumEncoder(AppActionResult)

  implicit val AppActionDecoder = enumDecoder(AppAction)
  implicit val AppActionEncoder = enumEncoder(AppAction)

  implicit val jwtPayloadtEncoder = new Encoder[JwtPayload] {
    override def apply(jwtPayload: JwtPayload): Json = Encoder.encodeJsonObject {
      JsonObject.fromMap(Map(
        "userId" -> jwtPayload.userId.asJson,
        "isAdmin" -> jwtPayload.isAdmin.asJson
      ))
    }
  }

  implicit val appEventEncoder = new Encoder[AppEvent] {
    override def apply(event: AppEvent): Json = Encoder.encodeJsonObject{
      JsonObject.fromMap{
        Map(
          "Timestamp" -> Encoder.encodeString(event.timestamp.toString),
          "IPAddress" -> Encoder.encodeString(event.ipAddress),
          "User"      -> Encoder.encodeInt(event.userId),
          "Type"      -> appEventTypeEncoder(event.appEventType),
          "Section"   -> appSectionEncoder(event.appSection),
          "Result"    -> appActionResultEncoder(event.appActionResult),
          "Severity"  -> appEventSeverityEncoder(event.appEventSeverity),
          "Action"    -> appActionEncoder(event.appAction)
        )
      }
    }
  }
}
