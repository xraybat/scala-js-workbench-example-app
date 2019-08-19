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

    // setup
    val renderer = canvas.getContext("2d")
                     .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = canvas.parentElement.clientWidth
    canvas.height = canvas.parentElement.clientHeight

    renderer.fillStyle = "#f8f8f8"
    renderer.fillRect(0, 0, canvas.width, canvas.height)

    // do it 
    renderer.fillStyle = "black"
    var down = false
    
    canvas.onmousedown = (e: dom.MouseEvent) => down = true
    canvas.onmouseup = (e: dom.MouseEvent) => down = false

    canvas.onmousemove = {
      (e: dom.MouseEvent) =>
        val rect = canvas.getBoundingClientRect()
        if (down)
          renderer.fillRect(e.clientX - rect.left,
                            e.clientY - rect.top,
                            10, 10)
    }

  } // main
} // ScalaJSExample