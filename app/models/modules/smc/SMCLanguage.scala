package models.modules.smc

import net.sf.smc.parser.SmcParser.TargetLanguage
import net.sf.smc.generator._

/**
 * Created by k_morishita on 2014/03/05.
 */
sealed abstract class Language {
  def name: String

  def language: TargetLanguage

  def generator: SMCOption => SmcCodeGenerator

  def headerGenerator: SMCOption => Option[SmcCodeGenerator] = (o: SMCOption) => None
}

case class ObjectiveC() extends Language {
  override def language: TargetLanguage = TargetLanguage.OBJECTIVE_C

  override def name: String = "ObjectiveC"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcObjCGenerator(options.toSmcOptions)

  override def headerGenerator: SMCOption => Option[SmcCodeGenerator] = (options: SMCOption) => Some(new SmcHeaderObjCGenerator(options.toSmcOptions))
}

case class Java() extends Language {
  override def language: TargetLanguage = TargetLanguage.JAVA

  override def name: String = "Java"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcJavaGenerator(options.toSmcOptions)
}

case class JavaScript() extends Language {
  override def language: TargetLanguage = TargetLanguage.JS

  override def name: String = "JavaScript"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcJSGenerator(options.toSmcOptions)
}

case class Python() extends Language {
  override def language: TargetLanguage = TargetLanguage.PYTHON

  override def name: String = "Python"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcPythonGenerator(options.toSmcOptions)
}

case class Ruby() extends Language {
  override def language: TargetLanguage = TargetLanguage.RUBY

  override def name: String = "Ruby"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcRubyGenerator(options.toSmcOptions)
}

case class Scala() extends Language {
  override def language: TargetLanguage = TargetLanguage.SCALA

  override def name: String = "Scala"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcScalaGenerator(options.toSmcOptions)
}

case class Graph() extends Language {
  override def language: TargetLanguage = TargetLanguage.GRAPH

  override def name: String = "Graph"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcGraphGenerator(options.toSmcOptions)
}

case class Table() extends Language {
  override def language: TargetLanguage = TargetLanguage.TABLE

  override def name: String = "Table"

  override def generator: SMCOption => SmcCodeGenerator = (options: SMCOption) => new SmcTableGenerator(options.toSmcOptions)
}
