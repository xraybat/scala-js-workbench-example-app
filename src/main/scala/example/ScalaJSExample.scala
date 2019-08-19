// clone of lihaoyi's https://github.com/lihaoyi/workbench for local experimentation, see http://www.lihaoyi.com/hands-on-scala-js/
//
// have upgraded various version nos. of components, scala, sbt.

package example

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random

@JSExport
object HelloWorld0 extends {
  @JSExport
  def main(target: html.Div): Unit = {
    println("main...helloworld0...")
    
    val (f, d) = ("fox", "dog")
        target.innerHTML = s"""
        <div>
          <h1>Hello World!</h1>
          <p>
            The quick brown <b>$f</b>
            jumps over the lazy <i>$d</b>
          </p>
        </div>
        """
  } // main
} // HelloWorld0