package de.htwg.se.vierGewinnt.aview

import de.htwg.se.vierGewinnt.model._
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.vierGewinnt.controller._


class TuiTest extends AnyWordSpec with Matchers {
  "a Tui" when {
    "his function processInputLine is to test" should {
      val controller = new Controller(new Grid(6,7))
      val tui = new Tui(controller)
      "input i1 will insert a value to column 1" in {
        tui.processInputLine("i 1")
        val a_grid = new Grid(6, 7).set(5, 1, 1)
        controller.grid must be(a_grid)
      }
      "input q will do nothing" in {
        tui.processInputLine("q")
      }
      "any non-configured input will do nothing" in {
        tui.processInputLine("test")
      }
    }
  }
}