package de.htwg.se.vierGewinnt.aview

import de.htwg.se.vierGewinnt.model._
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TuiSpec extends AnyWordSpec with Matchers {
  "a Tui" when {
    "his function processInputLine is to test" should {
      val tui = new Tui
      "input n will create a new grid" in {
        val new_grid_small = tui.processInputLine("n small", new Grid(2,3))._1
        new_grid_small must be(new Grid(6,7))
        val new_grid_middle = tui.processInputLine("n middle", new Grid(2,3))._1
        new_grid_middle must be(new Grid(10,11))
        val new_grid_huge = tui.processInputLine("n huge", new Grid(2,3))._1
        new_grid_huge must be(new Grid(16,17))
      }
      "input i1 will insert a value to column 1" in {
        val new_grid = tui.processInputLine("i1", new Grid(6,7))._1
        val a_grid = new Grid(6,7).set(5, 1, 1)
        new_grid must be(a_grid)
      }
      "input i1 will return a error because this column is full" in {
        val error = tui.processInputLine("i1", Grid(new Matrix[Cell](Vector(Vector(Cell(0),Cell(2)),Vector(Cell(0),Cell(1))))))._2
        error must be((-1,-1))
      }
    }
    "his function checkBottom is to test" should {
      val tui = new Tui
      "insert at the location in the grid" in {
        val a_grid = new Grid(6,7).set(5, 1, 1)
        val new_grid = tui.bodenCheck(new Grid(6,7), 1, 1)
        new_grid._1 must be(a_grid)
        new_grid._2 must be(5)
      }
      "insertion failed because the column is full" in {
        val new_grid = tui.bodenCheck(Grid(new Matrix[Cell](Vector(Vector(Cell(0),Cell(2)),Vector(Cell(0),Cell(1))))), 1, 1)
        new_grid._2 must be(-1)
      }
    }
    "his function check4number is to test" should {
      val tui = new Tui
      "return true" in {
        tui.viererCheck(Vector(Cell(1),Cell(1),Cell(1),Cell(1))) must be(true)
      }
      "return false" in {
        tui.viererCheck(Vector(Cell(0),Cell(1),Cell(0),Cell(1),Cell(0))) must be(false)
        tui.viererCheck(Vector(Cell(1),Cell(1),Cell(0),Cell(1),Cell(1))) must be(false)
        tui.viererCheck(Vector(Cell(2),Cell(1),Cell(0),Cell(1),Cell(2))) must be(false)
        tui.viererCheck(Vector(Cell(1),Cell(1),Cell(2),Cell(1),Cell(1))) must be(false)
      }
    }
  }
}