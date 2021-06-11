package de.htwg.se.vierGewinnt.controller

import de.htwg.se.vierGewinnt.controller.controllerBase._
import de.htwg.se.vierGewinnt.model.gridComp.gridBase.Grid
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerTest extends AnyWordSpec with Matchers {

  "a controller" when {
    "observed by an Observer" should {
      val aGrid = new Grid(2, 3)
      val controller = new Controller(aGrid)

      "test the checkWinner function" in {
        controller.check4Win(0,0) must be(false)
        controller.check4Win(1,0) must be(false)
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