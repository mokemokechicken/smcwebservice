package models.modules.smc

import net.sf.smc.parser.{SmcMessage, SmcParser}
import java.io.{PrintStream, ByteArrayOutputStream, ByteArrayInputStream}
import scala.collection.JavaConversions._
import net.sf.smc.SmcSyntaxChecker
import net.sf.smc.generator._
import net.sf.smc.model.SmcFSM

/**
 * Created by k_morishita on 2014/03/05.
 */
class SMCConverter(val name: String, val language: Language) {
  private val CHARSET: String = "UTF-8"

  def generate(smc: String): SMCConvertResult = {
    val parser = new SmcParser(name, new ByteArrayInputStream(smc.getBytes(CHARSET)), language.language, false)

    val fsm = parser.parse()
    if (fsm == null) {
      throw new SMCConvertException(parser.getMessages().toList)
    }

    val checker = new SmcSyntaxChecker(name, language.language)
    fsm.accept(checker)
    if (checker.isValid() == false) {
      throw new SMCConvertException(checker.getMessages().toList)
    }

    generateCode(fsm)
  }

  ///////////////////////////////////////////
  ///////// private
  ///////////////////////////////////////////

  private def generateCode(fsm: SmcFSM): SMCConvertResult = {
    System.setProperty("line.separator", "\n")
    val options = SMCOption(srcFileBase = name, targetFileBase = name)
    new SMCConvertResult(generateImplementCode(fsm, options), generateHeaderCode(fsm, options))
  }

  private def generateHeaderCode(fsm: SmcFSM, options: SMCOption): Option[String] = language.headerGenerator(options) match {
    case Some(g) => Some(generateTheCode(fsm, g))
    case None => None
  }

  private def generateImplementCode(fsm: SmcFSM, options: SMCOption): String = generateTheCode(fsm, language.generator(options))

  private def generateTheCode(fsm: SmcFSM, generator: SmcCodeGenerator): String = {
    val baos = new ByteArrayOutputStream()
    val ps = new PrintStream(baos)
    generator.setSource(ps)
    fsm.accept(generator)
    baos.flush()
    val str = baos.toString(CHARSET)
    baos.close()
    str
  }

}

class SMCConvertResult(val body: String, val header: Option[String])


class SMCConvertException(val messages: List[SmcMessage]) extends Exception


