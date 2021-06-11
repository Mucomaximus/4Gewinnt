package de.htwg.se.vierGewinnt

import de.htwg.se.vierGewinnt.controller.controllerBase._
import de.htwg.se.vierGewinnt.aview.Tui
import de.htwg.se.vierGewinnt.aview.gui._
import de.htwg.se.vierGewinnt.model.gridComp.gridBase.Grid

object vierGewinnt {
  val controller = new Controller(new Grid(5,6))
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)

  def main(args: Array[String]): Unit = {
    tui.processInputLineStart()
  }
}