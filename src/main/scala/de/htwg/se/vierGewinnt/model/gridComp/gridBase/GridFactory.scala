package de.htwg.se.vierGewinnt.model.gridComp.gridBase

import de.htwg.se.vierGewinnt.model.gridComp.GridInterface

object GridFactory {
  def getGrid(s: String): GridInterface = {
    if (s == "Small") new Grid(5, 6)
    else if (s == "Middle") new Grid(9, 10)
    else new Grid(15, 16)
  }
}
