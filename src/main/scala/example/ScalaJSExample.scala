// clone of lihaoyi's https://github.com/lihaoyi/workbench for local experimentation, see http://www.lihaoyi.com/hands-on-scala-js/
//
// have upgraded various version nos. of components, scala, sbt.

package example

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random

case class Point(x: Int, y: Int) {
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
    canvas.height = 400

    renderer.font = "50px sans-serif"
    renderer.textAlign = "center"
    renderer.textBaseline = "middle"

    // do it 
    val obstacleGap = 200 // gap between the approaching obstacles
    val holeSize = 50     // size of the hole in each obstacle you must go through
    val gravity = 0.1     // y acceleration of the player

    var playerY = canvas.height / 2.0 // y position of the player; X is fixed
    var playerV = 0.0                 // y velocity of the player

    // whether the player is dead or not;
    // 0 means alive, >0 is number of frames before respawning
    var dead = 0
    // what frame this is; used to keep track
    // of where the obstacles should be positioned
    var frame = -50
    // list of each obstacle, storing only the Y position of the hole.
    // the X position of the obstacle is calculated by its position in the
    // queue and in the current frame.
    val obstacles = collection.mutable.Queue.empty[Int]

    def runLive() = {
      frame += 2

      // create new obstacles, or kill old ones as necessary
      if (frame >= 0 && frame % obstacleGap == 0)
        obstacles.enqueue(Random.nextInt(canvas.height - 2 * holeSize) + holeSize)
      if (obstacles.length > 7) {
        obstacles.dequeue()
        frame -= obstacleGap
      }

      // apply physics
      playerY = playerY + playerV
      playerV = playerV + gravity

      // render obstacles, and check for collision
      renderer.fillStyle = "darkblue"
      for ((holeY, i) <- obstacles.zipWithIndex) {
        // where each obstacle appears depends on what frame it is.
        // this is what keeps the obstacles moving to the left as time passes.
        val holeX = i * obstacleGap - frame + canvas.width
        renderer.fillRect(holeX, 0, 5, holeY - holeSize)
        renderer.fillRect(
          holeX, holeY + holeSize, 5, canvas.height - holeY - holeSize
        )

        // kill the player if he hits some obstacle
        if (math.abs(holeX - canvas.width/2) < 5 &&
          math.abs(holeY - playerY) > holeSize) {
          dead = 50
        }
      } // for

      // render player
      renderer.fillStyle = "darkgreen"
      renderer.fillRect(canvas.width / 2 - 5, playerY - 5, 10, 10)

      // check for out-of-bounds player
      if (playerY < 0 || playerY > canvas.height) {
        dead = 50
      }
    } // runLive

    def runDead() = {
      playerY = canvas.height / 2
      playerV = 0
      frame = -50
      obstacles.clear()
      dead -= 1
      renderer.fillStyle = "darkred"
      renderer.fillText("Game Over", canvas.width / 2, canvas.height / 2)

    } // runDead

    def run() = {
      renderer.clearRect(0, 0, canvas.width, canvas.height)
      if (dead > 0) runDead()
      else runLive()
    }

    dom.window.setInterval(() => run, 20)
    
    canvas.onclick = (e: dom.MouseEvent) => {
      playerV -= 5
    }
  } // main
} // ScalaJSExample