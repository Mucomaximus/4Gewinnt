package de.htwg.se.vierGewinnt.controller

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.vierGewinnt.util._
import de.htwg.se.vierGewinnt.model.Grid

class ControllerTest extends AnyWordSpec with Matchers {

  "a controller" when {
    "observed by an Observer" must {
      val aGrid = new Grid(2, 3)
      val controller = new Controller(aGrid)
      val observer = new Observer() {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update(): Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createEmptyGrid("Small")
        observer.updated must be(true)
        controller.grid.size must be(30)
      }
      "notify its Observer after setting a cell" in {
        controller.setBottomVal(0)
        observer.updated must be(true)
        controller.grid.cell(4, 0).value must be(1)
        controller.setBottomVal(1)
        observer.updated must be(true)
        controller.grid.cell(4, 1).value must be(1)
        controller.setBottomVal(2)
        observer.updated must be(true)
        controller.grid.cell(4, 2).value must be(1)
        controller.setBottomVal(3)
        observer.updated must be(true)
        controller.grid.cell(4, 3).value must be(1)
      }
      "test the checkWinner function" in {
        controller.check4Win(0, 0) must be(false)
        controller.check4Win(4, 0) must be(true)
      }
      "test the getTrun function" in {
        controller.getTurn(0) must be(true)
      }
      "test the changeTurn function" in {
        controller.changeTurn()
        controller.getTurn(0) must be(false)
      }
      "test the currentPlayer function" in {
        controller.currentPlayer() must be(2)
      }
    }
  }
}