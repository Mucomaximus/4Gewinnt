package de.htwg.se.vierGewinnt

import de.htwg.se.connect_four.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.vierGewinnt.aview.Tui
import de.htwg.se.vierGewinnt.model.Grid
import de.htwg.se.vierGewinnt.aview.gui._

object vierGewinnt {
  val controller = new Controller(new Grid(6,7))
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)

  def main(args: Array[String]): Unit = {
    tui.processInputLineStart()
  }
}