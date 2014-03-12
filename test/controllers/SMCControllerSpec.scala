package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.JsValue

/**
 * Specs2 tests
 */
class SMCControllerSpec extends Specification with Tables {
  "SMCController with normal request body" should {
    "postSMC(java) should return Content-Type=application/json" in new NormalReqBody {
      val result = controllers.SMCController.postSMC("java")(request)
      contentType(result) must beSome("application/json")
    }

    "postSMC(java) should return with Access-Control-Allow-Origin: *" in new NormalReqBody {
      val result = controllers.SMCController.postSMC("java")(request)
      header(ACCESS_CONTROL_ALLOW_ORIGIN, result) must beSome("*")
    }

    "postSMC(lang) should status=200 if lang is supported" in new NormalReqBody {
        "lang" | "status" |
        "java" ! 200 |
        "graph" ! 200 |
        "js" ! 200 |
        "javascript" ! 200 |
        "objc" ! 200 |
        "objectivec" ! 200 |
        "python" ! 200 |
        "ruby" ! 200 |
        "scala" ! 200 |
        "table" ! 200 |
        "cat"  ! 400 |>
        { (lang, st) =>
          status(controllers.SMCController.postSMC(lang)(request)) mustEqual(st)
        }
    }

    "postSMC('ObjC', 'MyAppFSM') should include '#import MyAppFSM.h' in impl response" in new NormalReqBody {
      val result = controllers.SMCController.postSMC("ObjC", Some("MyAppFSM"))(request)
      (contentAsJson(result) \ "impl").as[String] must contain("""#import "MyAppFSM.h"""")
    }
  }
}

trait NormalReqBody extends WithApplication with SMCSample {
  implicit val request = FakeRequest().withBody(EX1)
}