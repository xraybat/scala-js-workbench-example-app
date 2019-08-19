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
object RerenderList extends {
  @JSExport
  def main(target: html.Div): Unit = {
    println("main...rerender-list...")

    val listings = Seq(
      "Apple", "Apricot", "Banana", "Cherry",
      "Mango", "Mangosteen", "Mandarin",
      "Grape", "Grapefruit", "Guava"
    )

    /*def renderListings = ul(
      for {
        fruit <- listings
        if fruit.toLowerCase.startsWith(
          box.value.toLowerCase
        )
      } yield li(fruit)
    ).render*/

    // with highlighting
    def renderListings = ul(
      for {
        fruit <- listings
        if fruit.toLowerCase.startsWith(
          box.value.toLowerCase
        )
      } yield {
        val (first, last) = fruit.splitAt(
          box.value.length
        )
        li(span(backgroundColor:="yellow", first), last)
      }
    ).render

    lazy val box = input(
      `type` := "text",
      placeholder := "type here!"
    ).render

    val output = div(renderListings).render

    box.onkeyup = (e: dom.Event) => {
      output.innerHTML = ""
      output.appendChild(renderListings)
    }

    target.appendChild(
      div(
        h1("search box!"),
        p("type here to filter the list of things below!"),
        div(box),
        output
      ).render
    )
  } // main
} // RerenderList
