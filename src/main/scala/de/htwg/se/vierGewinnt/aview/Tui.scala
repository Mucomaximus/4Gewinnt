package de.htwg.se.vierGewinnt.aview

import de.htwg.se.vierGewinnt.util.Observer
import de.htwg.se.vierGewinnt.controller._
import scala.io.StdIn


class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var winnerCheck = false
  var player1 = ""
  var player2 = ""
  var input = ""


  def processInputLineStart(): Unit = {

    println("Player 1 please give ur name:")
    player1 = StdIn.readLine()
    println("Player 2 please give ur name:")
    player2 = StdIn.readLine()
    println(s"$player1 and $player2, Welcome to vier Gewinnt")

    processInputLineLoop()
  }


  def processInputLineLoop(): Unit = {
    do {
      if (controller.getTurn(0)) {
        println(s"$player1, its your turn!")
      } else if (controller.getTurn(1)) {
        println(s"$player2, its your turn!")
      }
      input = StdIn.readLine()
      processInputLine(input)
    } while (input != "q" && !winnerCheck)
  }


  def processInputLine(input: String): Unit = {
    input match {
      case "q" => println("Spiel beendet!")
      case "s" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid("Small")
      case "m" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid("Middle")
      case "h" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid("Huge")
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil =>
            val col = toInt(column)
            if (col == None){
              println(column + " is Not a value")
              return
            }
            val row_number = controller.setBottomVal(col.get)
            if (row_number == -1) {
              println("Wrong Input")
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

  def toInt(input: Char): Option[Int] = {
    try {
      Some(Integer.parseInt(input.toString.trim))
    } catch {
      case e: Exception => None
    }
  }


  override def update(): Unit = {
    println(controller.gridToString)
    println(Status.getMessage(controller.gameStatus.mystate.gameStatus))
    if (controller.gameStatus.mystate.gameStatus.equals(Status.WON))
      winnerCheck = true
  }
}

