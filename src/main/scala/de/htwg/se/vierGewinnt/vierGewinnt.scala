package de.htwg.se.vierGewinnt

import de.htwg.se.vierGewinnt.aview.Tui
import de.htwg.se.vierGewinnt.controller.Controller
import de.htwg.se.vierGewinnt.model.Grid
import de.htwg.se.vierGewinnt.aview.gui._

object vierGewinnt {
  val controller = new Controller(new Grid(6, 7))
  var grid = new Grid(5, 6)
  val gui = new SwingGui(controller)
  val tui = new Tui(controller)

  def main(args: Array[String]): Unit = {
    gui.HelloWorld.top
    tui.processInputLineStart()

  }
}
