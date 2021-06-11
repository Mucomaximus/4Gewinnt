package de.htwg.se.vierGewinnt.aview

import de.htwg.se.vierGewinnt.controller.GameStatus
import de.htwg.se.vierGewinnt.controller.{CellChanged, ControllerInterface, GridSizeChanged, WinEvent}

import scala.swing.Reactor
import scala.io.StdIn

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)
  var winnerCheck = false
  var player1 = "Player1"
  var player2 = "Player2"
  var input = ""

  def processInputLineStart(): Unit = {
    println("Player 1 please type in your name:")
    player1 = StdIn.readLine()
    println("Player 2 please type in your name:")
    player2 = StdIn.readLine()
    println(s"$player1 and $player2, Welcome to Connect Four")
    println(controller.gridToString)

    processInputLineLoop()
  }

  def processInputLineLoop(): Unit = {
    do {
      if (winnerCheck) {
        println("Start a new game please!")
        input = StdIn.readLine()
        processInputLine(input)
        winnerCheck = false
      }

      if (controller.getTurn(0)) {
        println(s"$player1, its your turn!")
      } else if (controller.getTurn(1)) {
        println(s"$player2, its your turn!")
      }
      input = StdIn.readLine()
      processInputLine(input)
    } while (input != "q")
  }

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => println("The game exit")
      case "n small" =>
        controller.resetPlayerList()
        controller.createEmptyGrid("Small")
      case "n middle" =>
        controller.resetPlayerList()
        controller.createEmptyGrid("Middle")
      case "n huge" =>
        controller.resetPlayerList()
        controller.createEmptyGrid("Huge")
      case "undo" => controller.undo
      case "redo" => controller.redo
      case _ =>
        if (winnerCheck) {
          println("please start a new game")
          return
        }
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil =>
            controller.setBottomVal(column.asDigit)
          case _ =>
            println("wrong input, repeat your turn!")
        }
    }
  }

  reactions += {
    case event: CellChanged => printTui()
    case event: GridSizeChanged => printTui()
    case event: WinEvent => printWinner
  }

  def printTui(): Unit = {
    println(controller.gridToString)
    println(GameStatus.message(controller.getGameStatus))
  }

  def printWinner():Unit = {
    println(controller.gridToString)
    if (controller.currentPlayer() == 1) {
      printf("%s is the winner!\n",player1)
    } else {
      printf("%s is the winner!\n", player2)
    }
    winnerCheck = true
  }
}
