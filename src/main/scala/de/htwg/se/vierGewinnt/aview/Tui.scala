package de.htwg.se.vierGewinnt.aview

import de.htwg.se.vierGewinnt.util.Observer
import de.htwg.se.vierGewinnt.controller._

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var winnerCheck = false

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => println("The game exit")
      case "n small" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid(6, 7)
      case "n middle" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid(10, 11)
      case "n huge" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid(16, 17)
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil =>
            val row_number = controller.setValueToBottom(column.asDigit)
            if (row_number == -1) {
              println("cant insert at this column, repeat your turn!")
              return
            }
            if (controller.checkWinner(row_number, column.asDigit)) {
              printf("The player%s is the winner!\n", controller.currentPlayer().toString)
              winnerCheck = true
            } else {
              controller.changeTurn()
            }
          case _ =>
            println("wrong input, repeat your turn!")
        }
    }
  }

  override def update(): Unit = println(controller.gridToString)
}