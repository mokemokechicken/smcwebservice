package models.modules.smc

import net.sf.smc.generator.SmcOptions

/**
 * Created by k_morishita on 2014/03/05.
 */

case class SMCOption(srcFileBase: String = "/tmp1",
                     targetFileBase: String = "/tmp2",
                     srcDirectory: String = "/tmp3",
                     headerDirectory: String = "/tmp4",
                     headerSuffix: String = "h",
                     castType: String = "dynamic_cast",
                     graphLevel: Int = 0,
                     serialFlag: Boolean = false,
                     debugLevel: Int = -1,
                     noExceptionFlag: Boolean = false,
                     noCatchFlag: Boolean = false,
                     noStreamsFlag: Boolean = false,
                     reflectFlag: Boolean = false,
                     syncFlag: Boolean = false,
                     genericFlag: Boolean = false,
                     java7Flag: Boolean = false,
                     accessLevel: String = AccessLevel.PUBLIC) {

  def toSmcOptions: SmcOptions = {
    new SmcOptions(srcFileBase, targetFileBase, srcDirectory, headerDirectory, headerSuffix, castType,
      graphLevel, serialFlag, debugLevel, noExceptionFlag, noCatchFlag, noStreamsFlag, reflectFlag,
      syncFlag, genericFlag, java7Flag, accessLevel)
  }
}

object AccessLevel {
  val PUBLIC: String = "public"
  val PROTECTED: String = "protected"
  val PACKAGE: String = "package"
  val PRIVATE: String = "private"
}





