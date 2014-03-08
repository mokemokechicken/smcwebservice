package controllers

import play.api.mvc.{Action, Controller}
import models.modules.smc._
import play.api.libs.json.Json

/**
 * Created by k_morishita on 2014/03/05.
 */
object SMCController extends Controller {
  implicit val fooWrites = Json.writes[SMCResponse]

  def postSMC(lang: String) = Action(parse.tolerantText) { implicit request =>
    {
      val body = request.body
      (LangString2Class.map(lang) match {
        case None => BadRequest(s"lang=$lang is not available")
        case Some(lng) => {
          val converter = new SMCConverter("name", lng)
          try {
            val result = converter.generate(body)
            Ok(Json.toJson(SMCResponse(result.header, result.body)))
          } catch {
            case e: SMCConvertException => BadRequest(e.showMessage())
          }
        }
      }).withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> "*")
    }
  }
}

case class SMCResponse(header: Option[String], impl: String)

object LangString2Class {
  def map(lang: String): Option[Language] = lang.toLowerCase() match {
    case "java" => Some(Java())
    case "graph" => Some(Graph())
    case "js" | "javascript" => Some(JavaScript())
    case "objc" | "objectivec" => Some(ObjectiveC())
    case "python" => Some(Python())
    case "ruby" => Some(Ruby())
    case "scala" => Some(Scala())
    case _ => None
  }
}