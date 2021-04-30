package de.htwg.se.vierGewinnt.model


import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec


class MatrixSpec extends AnyWordSpec with Matchers {
  "A Matrix is a tailor-made immutable data type that contains a two-dimentional Vector of Cells. A Matrix" when {
    "empty" should {
      "be created by 4x5 matrix filled with empty cells" in {
        val emtpyMatrix = new Matrix[Cell](4, 5, Cell(0))
        emtpyMatrix.row must be(4)
        emtpyMatrix.col must be(5)
        emtpyMatrix.size must be(20)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector(Cell(0))))
        testMatrix.size must be(1)
      }
    }

    "filled" should {
      val matrix = Matrix(Vector(Vector(Cell(0),Cell(1)), Vector(Cell(0), Cell(2))))
      "give a access to its cells" in {
        matrix.cell(0,0) must be(Cell(0))
        matrix.cell(0,1) must be(Cell(1))
        matrix.cell(1,0) must be(Cell(0))
        matrix.cell(1,1) must be(Cell(2))
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0,0,Cell(1))
        matrix.cell(0,0) must be(Cell(0))
        returnedMatrix.cell(0,0) must be(Cell(1))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(Cell(2))
        returnedMatrix.cell(0,0) must be(Cell(2))
        returnedMatrix.cell(0,1) must be(Cell(2))
      }
    }
  }
}
