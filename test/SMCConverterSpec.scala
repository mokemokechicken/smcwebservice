package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import models.modules.smc._
import org.specs2.specification.Scope
import models.modules.smc.ObjectiveC

/**
 * Specs2 tests
 */
class SMCConverterSpec extends Specification {
  "SMCConverter" should {
    "generate should return SMCConvertResult" in new Java {
      obj.generate(EX1) must beAnInstanceOf[SMCConvertResult]
    }

    "generate should throw Exception when error" in new Java {
      obj.generate(EX2) must throwA[SMCConvertException]
    }

    // Check Header
    "generate should return Header" in new ObjC {
      obj.generate(EX1).header.get must contain("@interface AppClassState : SMCState")
    }
    "generate should not return Header" in new Java {
      obj.generate(EX1).header must beNone
    }
    "generate should not return Header" in new Python {
      obj.generate(EX1).header must beNone
    }
    "generate should not return Header" in new Ruby {
      obj.generate(EX1).header must beNone
    }
    "generate should not return Header" in new JS {
      obj.generate(EX1).header must beNone
    }

    // Check Implementation Code
    "generate should include 'Start' in impl code" in new Java {
      obj.generate(EX1).body must contain("Start")
    }

    "generate should include 'Start' in impl code" in new ObjC {
      obj.generate(EX1).body must contain("Start")
    }

    "generate should include 'Start' in impl code" in new Python {
      obj.generate(EX1).body must contain("Start")
    }

    "generate should include 'Start' in impl code" in new Ruby {
      obj.generate(EX1).body must contain("Start")
    }

    "generate should include 'Start' in impl code" in new JS {
      obj.generate(EX1).body must contain("Start")
    }
  }

}

trait SMCScope extends Scope with SMCSample
trait Java extends SMCScope { val obj = new SMCConverter("AppClass", Java())}
trait ObjC extends SMCScope { val obj = new SMCConverter("AppClass", ObjectiveC())}
trait Python extends SMCScope { val obj = new SMCConverter("AppClass", Python())}
trait Ruby extends SMCScope { val obj = new SMCConverter("AppClass", Ruby())}
trait JS extends SMCScope { val obj = new SMCConverter("AppClass", JS())}

trait SMCSample {
  val EX1 = """
%start Map1::Start
%class AppClass

%map Map1
%%
// State                Transition              End State               Action(s)
Start {
                                Zero                    Zeros                   {}
                                One                             Ones                    {}
                                Unknown                 Error                   {}
                                EOS                             OK                              {Acceptable();}
}

Zeros {
                                Zero                    Zeros                   {}
                                One                             Ones                    {}
                                Unknown                 Error                   {}
                                EOS                             OK                              {Acceptable();}
}

Ones {
                                Zero                    Error                   {}
                                One                             Ones                    {}
                                Unknown                 Error                   {}
                                EOS                             OK                              {Acceptable();}
}

OK {}

Error {
                                Zero                    nil                             {}
                                One                             nil                             {}
                                Unknown                 nil                             {}
                                EOS                             nil                             {Unacceptable();}
}

%%"""

  val EX2 =
    """kk
      |%start Map1::Start
      |%class AppClass
      |
      |%map Map1
      |%%
      |// State                Transition              End State               Action(s)
      |Start {
    """.stripMargin
}