// clone of lihaoyi's https://github.com/lihaoyi/workbench for local experimentation, see http://www.lihaoyi.com/hands-on-scala-js/
//
// have upgraded various version nos. of components, scala, sbt.

package example

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random

case class Point(x: Int, y: Int){
  def +(p: Point) = Point(x + p.x, y + p.y)
  def /(d: Int) = Point(x / d, y / d)
}

@JSExport
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    println("main...")

    val ctx = canvas.getContext("2d")
                    .asInstanceOf[dom.CanvasRenderingContext2D]

    var count = 0
    var p = Point(0, 0)
    val corners = Seq(Point(255, 255),
                      Point(0, 255),
                      Point(128, 0))

    def clear() = {
      ctx.fillStyle = "white"
      ctx.fillRect(0, 0, 255, 255)
    }

    def run = for (i <- 0 until 10) {
      if (count % 3000 == 0)
        clear()
      count += 1
      p = (p + corners(Random.nextInt(3))) / 2

      val height = 512.0 / (255 + p.y)
      val r = (p.x * height).toInt
      val g = ((255 - p.x) * height).toInt
      val b = p.y

      ctx.fillStyle = s"rgb($g, $r, $b)"
      ctx.fillRect(p.x, p.y, 1, 1)

    } // run

    dom.window.setInterval(() => run, 50)

  } // main
} // ScalaJSExample