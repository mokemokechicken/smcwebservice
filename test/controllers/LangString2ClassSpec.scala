package controllers

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import org.specs2.matcher.DataTables
import models.modules.smc._

/**
 * Created by k_morishita on 2014/03/07.
 */
class LangString2ClassSpec extends Specification {
  def check(lang: String, klass: Language) = LangString2Class.map(lang) must beSome(klass)

  "LangString2Class.map(lang) => class" should  {
    "be unknown => None" in {LangString2Class.map("unknown") must beNone}
    "be java => Java" in {check("java", Java())}
    "be Java => Java" in {check("Java", Java())}
    "be JAVA => Java" in {check("JAVA", Java())}
    "be objc => ObjectiveC" in {check("objc", ObjectiveC())}
    "be objectivec => ObjectiveC" in {check("objectivec", ObjectiveC())}
    "be python => Python" in {check("python", Python())}
    "be RUBY => Ruby" in {check("RUBY", Ruby())}
    "be JS => JavaScript" in {check("JS", JavaScript())}
    "be JAVASCRIPT => JavaScript" in {check("JavaScript", JavaScript())}
    "be Graph => Graph" in {check("Graph", Graph())}
    "be scala => Scala" in {check("scala", Scala())}
  }

}
