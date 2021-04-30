package de.htwg.se.vierGewinnt.aview

import de.htwg.se.vierGewinnt.util.Observer
import de.htwg.se.vierGewinnt.controller._

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var winnerCheck = false

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => println("Spiel beendet!")
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil =>
            val row_number = controller.setBottomVal(column.asDigit)
            if (row_number == -1) {
              println("Column full!")
              return
            }
            if (controller.check4Win(row_number, column.asDigit)) {
              printf("The player %s won!\n", controller.currentPlayer().toString)
              winnerCheck = true
            } else {
              controller.changeTurn()
            }
          case _ =>
            println("Wrong input, repeat!")
        }
    }
  }

  override def update(): Unit = println(controller.gridToString)
}