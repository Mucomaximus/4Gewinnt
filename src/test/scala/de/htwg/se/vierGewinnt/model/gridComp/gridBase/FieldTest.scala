package de.htwg.se.vierGewinnt.model.gridComp.gridBase

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FieldTest extends AnyWordSpec with Matchers {
  "a field is a row or a column or a diagonal of a grid" when {
    val field = Field(Vector(Cell(1), Cell(2),Cell(3),Cell(5)))
    "cell function is to test" in {
      field.getCell(0) must be(Cell(1))
      field.getCell(3) must be(Cell(5))
    }
    "getCells function is to test" in {
      field.getCells must be(Vector(Cell(1), Cell(2),Cell(3),Cell(5)))
    }
    "reverseD function is to test" in {
      field.reverseD must be(Vector(Cell(5), Cell(3),Cell(2),Cell(1)))
    }
  }
}