package controllers

import play.api.mvc.{Action, Controller}
import models.modules.smc.{SMCConverter, Java}
import play.api.libs.json.Json

/**
 * Created by k_morishita on 2014/03/05.
 */
object SMCController extends Controller {
  implicit val fooWrites = Json.writes[SMCResponse]

  def postSMC(lang: String) = Action(parse.tolerantText) { request =>
    val body = request.body
    val converter = new SMCConverter("name", Java())
    val result = converter.generate(body)
    //
    Ok(Json.toJson(SMCResponse(result.header, result.body)))
  }
}

case class SMCResponse(header: Option[String], impl: String)
