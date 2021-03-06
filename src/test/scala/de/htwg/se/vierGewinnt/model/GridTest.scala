package de.htwg.se.vierGewinnt.model

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GridTest extends AnyWordSpec with Matchers {
  "A Grid is the playingfield of Connect Four. A Grid" when {
    val smallGrid = Grid(new Matrix[Cell](Vector(Vector(Cell(1), Cell(2), Cell(3)), Vector(Cell(4), Cell(5), Cell(6)), Vector(Cell(7), Cell(8), Cell(9)))))
    val grid = new Grid(2,3)
    val tinyGrid = Grid(new Matrix[Cell](Vector(Vector(Cell(1)))))
    val aGrid = new Grid(4, 5)
    "toString function tested" in {
      val a_string = "0 0 0 " + System.lineSeparator() + "0 0 0 " + System.lineSeparator()
      grid.toString must be(a_string)
    }
    "give access to its Cells" in {
      aGrid.cell(0, 0) must be(Cell(0))
      aGrid.cell(0, 1) must be(Cell(0))
      aGrid.cell(1, 0) must be(Cell(0))
      aGrid.cell(1, 1) must be(Cell(0))
    }
    "allow to set individual Cells and remain immutable" in {
      val changedGrid = aGrid.set(0, 0, 1)
      changedGrid.cell(0, 0) must be(Cell(1))
      aGrid.cell(0, 0) must be(Cell(0))
    }
    "have the right values in the right places" in {
      smallGrid.cell(0, 0) must be(Cell(1))
      smallGrid.cell(0, 1) must  be(Cell(2))
      smallGrid.cell(1, 0) must be(Cell(4))
      smallGrid.cell(1, 1) must be(Cell(5))
    }
    "have Field with the right Cells" in {
      tinyGrid.row(0).getCell(0) must be(Cell(1))
      tinyGrid.col(0).getCell(0) must be(Cell(1))

      smallGrid.row(0).getCell(0) must be(Cell(1))
      smallGrid.row(0).getCell(1) must be(Cell(2))
      smallGrid.row(1).getCell(0) must be(Cell(4))
      smallGrid.row(1).getCell(1) must be(Cell(5))
      smallGrid.col(0).getCell(0) must be(Cell(1))
      smallGrid.col(0).getCell(1) must be(Cell(4))
      smallGrid.col(1).getCell(0) must be(Cell(2))
      smallGrid.col(1).getCell(1) must be(Cell(5))
      smallGrid.col(2).getCells must be(Vector(Cell(3), Cell(6), Cell(9)))
    }
    "have Field with the diagonal" in {
      smallGrid.left_dia(1,0).getCell(0) must be(Cell(4))
      smallGrid.left_dia(1, 0).getCell(1) must be(Cell(2))
      smallGrid.right_dia(1,0).getCell(0) must be(Cell(4))
      smallGrid.right_dia(1,0).getCell(1) must be(Cell(8))
      smallGrid.right_dia(1,1).getCell(0) must be(Cell(1))
      smallGrid.right_dia(1,1).getCell(1) must be(Cell(5))
      smallGrid.left_dia(1,1).getCells must be(Vector(Cell(7),Cell(5),Cell(3)))
      smallGrid.right_dia(1,1).getCells must be(Vector(Cell(1), Cell(5), Cell(9)))
      smallGrid.left_dia(1,0).getCells must be(Vector(Cell(4), Cell(2)))
      smallGrid.right_dia(1,0).getCells must be(Vector(Cell(4), Cell(8)))
    }
  }
}