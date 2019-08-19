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
object UserInput0 extends {
  @JSExport
  def main(target: html.Div): Unit = {
    println("main...userinput0...")

    val box = input(
      `type` := "text",
      placeholder :=  "Type here!"
    ).render

    val output = span.render

    box.onkeyup = (e: dom.Event) => {
      output.textContent = box.value.toUpperCase
    }

    target.appendChild(
      div(
        h1("capital box!"),
        p("type here and have it capitalized!"),
        div(box),
        div(output)
      ).render
    )
  } // main
} // UserInput0
