// clone of lihaoyi's https://github.com/lihaoyi/workbench for local experimentation, see http://www.lihaoyi.com/hands-on-scala-js/
//
// have upgraded various version nos. of components, scala, sbt.

package example

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random
import scalajs.js

case class Point(x: Int, y: Int){
  def +(p: Point) = Point(x + p.x, y + p.y)
  def /(d: Int) = Point(x / d, y / d)
}

@JSExport
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    println("main...")

    // setup
    val renderer = canvas.getContext("2d")
                     .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = canvas.parentElement.clientWidth
    canvas.height = canvas.parentElement.clientHeight

    val gradient = renderer.createLinearGradient(
      canvas.width / 2 - 100, 0, canvas.width / 2 + 100, 0)
    gradient.addColorStop(0, "red")
    gradient.addColorStop(0.5, "green")
    gradient.addColorStop(1, "blue")
    renderer.fillStyle = gradient
    renderer.textAlign = "center"
    renderer.textBaseline = "middle"

    // do it 
    def format(n: Int) = f"$n%02d"

    def render() = {
      val date = new js.Date()
      renderer.clearRect(0, 0, canvas.width, canvas.height)
      renderer.font = "75px sans-serif"
      renderer.fillText(Seq(date.getHours(),
                            date.getMinutes(),
                            date.getSeconds()).map(format).mkString(":"),
                        canvas.width / 2,
                        canvas.height / 2)
    }

    dom.window.setInterval(() => render, 1000)

  } // main
} // ScalaJSExample