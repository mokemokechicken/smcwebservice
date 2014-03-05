package models.modules.smc

import net.sf.smc.generator.SmcOptions

/**
 * Created by k_morishita on 2014/03/05.
 */

case class SMCOption(srcFileBase: String = "/tmp",
                     targetFileBase: String = "/tmp",
                     srcDirectory: String = "/tmp",
                     headerDirectory: String = "/tmp",
                     headerSuffix: String = ".h",
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
                     accessLevel: String = "public") {

  def toSmcOptions: SmcOptions = {
    new SmcOptions(srcFileBase, srcFileBase, targetFileBase, srcDirectory, headerDirectory, headerSuffix,
      graphLevel, serialFlag, debugLevel, noExceptionFlag, noCatchFlag, noStreamsFlag, reflectFlag,
      syncFlag, genericFlag, java7Flag, accessLevel)
  }
}

