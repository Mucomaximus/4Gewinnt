package de.htwg.se.vierGewinnt.model

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec


class CellTest extends AnyWordSpec with Matchers {

  "a Cell" when {
    "not set to any value" must {
      val emtpyCell = Cell(0)
      "habe value 0" in {
        emtpyCell.value must be(0)
      }
      "not be set" in {
        emtpyCell.isSet must be(false)
      }
    }
    "set to a specific value" must {
      val nonEmptyCell = Cell(1)
      "return that value" in {
        nonEmptyCell.value must be(1)
      }
      "be set" in {
        nonEmptyCell.isSet must be(true)
      }
    }

    "converted to a String" must {
      val nonEmptyCell1 = Cell(1)
      "return" in {
        nonEmptyCell1.toString must be("1")
      }
    }
  }
}
