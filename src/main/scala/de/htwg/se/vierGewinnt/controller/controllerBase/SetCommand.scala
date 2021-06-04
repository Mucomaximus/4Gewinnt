package de.htwg.se.vierGewinnt.controller.controllerBase

import de.htwg.se.vierGewinnt.controller.ControllerInterface
import de.htwg.se.vierGewinnt.util.Command

class SetCommand(row: Int, col:Int, value:Int, controller: ControllerInterface) extends Command {
  override def doStep: Unit = {
    controller.grid = controller.grid.set(row,col,value)
  }

  override def undoStep: Unit = {
    controller.grid = controller.grid.set(row, col, 0)
    controller.changeTurn()
  }

  override def redoStep: Unit = {
    controller.grid = controller.grid.set(row, col, value)
    controller.changeTurn()
  }
}
