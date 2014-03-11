package controllers

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import org.specs2.matcher.DataTables
import models.modules.smc._

/**
 * Created by k_morishita on 2014/03/07.
 */
class LangString2ClassSpec extends Specification with DataTables {
  def check(lang: String, klass: Language) = LangString2Class.map(lang) must beSome(klass)

  "LangString2Class.map('unknown') => None" should  {
    "be unknown => None" in {LangString2Class.map("unknown") must beNone}
  }

  "LangString2Class.map(lang) => class" should  {
    "str"         || "class" |
    "java"        !! Java() |
    "JAVA"        !! Java() |
    "objc"        !! ObjectiveC() |
    "objectivec"  !! ObjectiveC() |
    "python"      !! Python() |
    "RUby"        !! Ruby() |
    "JS"          !! JavaScript() |
    "JAVASCRIPT"  !! JavaScript() |
    "Graph"       !! Graph() |
    "SCALA"       !! Scala() |
    "table"       !! models.modules.smc.Table() |>
    { (s, klass) =>
      LangString2Class.map(s) must beSome(klass)
    }
  }
}
