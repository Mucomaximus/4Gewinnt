package de.htwg.se.vierGewinnt.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, WIN, PLAYER1TURN, PLAYER2TURN = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    WIN -> "Game Over",
  )

  def message(gameStatus: GameStatus) = {
      IDLE -> ""
      WIN -> "Game Over"
  }
}