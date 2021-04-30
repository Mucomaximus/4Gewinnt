package de.htwg.se.vierGewinnt

import de.htwg.se.vierGewinnt.model._
import de.htwg.se.vierGewinnt.aview._

import scala.io.StdIn.readLine
import util.control.Breaks._

object vierGewinnt {
  var grid = new Grid(6,7)
  val tui = new Tui

  def main(args: Array[String]): Unit = {

    var nameSpieler1: String = ""
    println("Player 1 please type in your name:")
    val player1 = Player(readLine())

    var nameSpieler2: String = ""
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
      nameSpieler1 = readLine()
      val result = tui.processInputLine(nameSpieler1, grid)
      grid = result._1
      val flag = result._2
      if (flag != (-1, -1) && !nameSpieler1.contains("n")) {
        if (tui.check4Vier(grid, flag._1, flag._2)) {
          println("player"+tui.currentPlayer() + " win!")
          break
        } else if (!nameSpieler1.contains("n")) {tui.changeTurn()}
      }
    } while (nameSpieler1 != "q")
  }
}
