package de.htwg.se.vierGewinnt

import de.htwg.se.vierGewinnt.model._
import de.htwg.se.vierGewinnt.aview._

import scala.io.StdIn.readLine
import util.control.Breaks._

object vierGewinnt {
  var grid = new Grid(6,7)
  val tui = new Tui

  def main(args: Array[String]): Unit = {
    var input: String = ""

    println("Player 1 please type in your name:")
    val player1 = Player(readLine())
    var input2: String = ""
    println("Player 2 please type in your name:")
    val player2 = Player(readLine())
    println(s"$player1 and $player2, Welcome to Connect Four")


    do {
      if (tui.getTurn(0)) {
        println(s"$player1, its your turn!")
      } else if (tui.getTurn(1)) {
        println(s"$player2, its your turn!")
      }
      println(grid.toString)
      input = readLine()
      val result = tui.processInputLine(input, grid)
      grid = result._1
      val flag = result._2
      if (flag != (-1, -1) && !input.contains("n")) {
        if (tui.check4Vier(grid, flag._1, flag._2)) {
          println("player"+tui.currentPlayer() + " win!")
          break
        } else if (!input.contains("n")) {tui.changeTurn()}
      }
    } while (input != "q")
  }
}
