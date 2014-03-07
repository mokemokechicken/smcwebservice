package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

/**
 * Specs2 tests
 */
class SMCControllerSpec extends Specification {

  "SMCController" should {

    "postSMC should return JSON" in new WithApplication {
      val result = controllers.SMCController.postSMC("java")(FakeRequest())
    }
  }
}