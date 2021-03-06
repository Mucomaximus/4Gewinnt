package de.htwg.se.vierGewinnt

import de.htwg.se.vierGewinnt.model._
import de.htwg.se.vierGewinnt.aview._


import scala.io.StdIn.readLine
import de.htwg.se.vierGewinnt.controller._

object vierGewinnt {
  val controller = new Controller(new Grid(6,7))
  var grid = new Grid(6,7)
  val tui = new Tui(controller)

  def main(args: Array[String]): Unit = {
    var input: String = ""

    println("Player 1 please give ur name:")
    val player1 = Player(readLine())
    println("Player 2 please give ur name:")
    val player2 = Player(readLine())
    println(s"$player1 and $player2, Welcome to Connect Four")

    println(controller.gridToString)
    do {
      if (controller.getTurn(0)) {
        println(s"$player1, your turn!")
      } else if (controller.getTurn(1)) {
        println(s"$player2, your turn!")
      }
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q" && !tui.winnerCheck)
  }
}