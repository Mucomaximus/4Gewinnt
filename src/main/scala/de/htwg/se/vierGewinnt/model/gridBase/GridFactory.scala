package de.htwg.se.vierGewinnt.model.gridBase

import de.htwg.se.vierGewinnt.model.GridInterface

object GridFactory {
  def getGrid(s: String): GridInterface = {
    if (s == "Small") new Grid(5, 6)
    else if (s == "Middle") new Grid(9, 10)
    else new Grid(15, 16)
  }
}
