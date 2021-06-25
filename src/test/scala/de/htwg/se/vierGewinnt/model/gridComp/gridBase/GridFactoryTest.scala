package de.htwg.se.vierGewinnt.model.gridComp.gridBase

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec


class GridFactoryTest extends AnyWordSpec with Matchers {
  "A Grid" when {
    val small = new Grid(5, 6)
    val middle = new Grid(8,9)
    val huge = new Grid(14,15)
    "small" in {
      small.size must be(30)
    }
    "middle" in {
      middle.size must be(72)
    }
    "huge" in {
      huge.size must be(210)
    }
  }
}
