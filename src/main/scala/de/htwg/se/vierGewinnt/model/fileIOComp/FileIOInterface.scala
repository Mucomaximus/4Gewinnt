package de.htwg.se.vierGewinnt.model.fileIOComp

import de.htwg.se.vierGewinnt.model.gridComp.GridInterface

trait FileIOInterface {

  def load: (GridInterface,Array[Boolean])
  def save(grid: GridInterface, players:Array[Boolean]): Unit

}
