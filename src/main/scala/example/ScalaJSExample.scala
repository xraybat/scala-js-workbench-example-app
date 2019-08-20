// clone of lihaoyi's https://github.com/lihaoyi/workbench for local experimentation, see http://www.lihaoyi.com/hands-on-scala-js/
//
// have upgraded various version nos. of components, scala, sbt.

package example

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random
import scalatags.JsDom.all._

@JSExport
object PlayExperiment extends {
  @JSExport
  def main(target: html.Div): Unit = {
    println("main...play-experiment...")

    val (animalA, animalB) = ("fox", "dog")
    target.appendChild(
      div(
        h1("Hello World!"),
        p("The quick brown ", b(animalA),
          " jumps over the lazy ", i(animalB), ".")
      ).render
    )
  } // main
} // PlayExperiment
