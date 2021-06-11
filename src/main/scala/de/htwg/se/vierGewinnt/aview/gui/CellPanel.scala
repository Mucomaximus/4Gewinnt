package de.htwg.se.vierGewinnt.aview.gui


import scala.swing._
import scala.swing.event._
import de.htwg.se.vierGewinnt.controller.{CellChanged, ControllerInterface, WinEvent}


class CellPanel(row: Int, column: Int, controller: ControllerInterface) extends FlowPanel {

  var winnerCheck = false

  contents += new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(80, 80)
    background = new Color(255, 255, 255)
    border = Swing.BeveledBorder(Swing.Lowered)
    listenTo(mouse.clicks)
    listenTo(controller)

    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) => {
        if (!winnerCheck) {
          controller.setBottomVal(column)
          repaint
        }
      }

      case e: CellChanged => {
        if (controller.getGridRow > row && controller.getGridCol > column) {
          this.background = if (controller.grid.cell(row, column).value == 0) {
            new Color(255, 255, 255)
          } else if (controller.grid.cell(row, column).value == 1) {
            new Color(255, 0, 0)
          } else {
            new Color(255, 255, 0)
          }
        }
        repaint
      }

      case d: WinEvent => {
        if (controller.getGridRow > row && controller.getGridCol > column) {
          this.background = if (controller.grid.cell(row, column).value == 0) {
            new Color(255, 255, 255)
          } else if (controller.grid.cell(row, column).value == 1) {
            new Color(255, 0, 0)
          } else {
            new Color(255, 255, 0)
          }
        }
        winnerCheck = true
        repaint
      }
    }
  }
}