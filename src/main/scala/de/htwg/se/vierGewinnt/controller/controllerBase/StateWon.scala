package de.htwg.se.vierGewinnt.controller.controllerBase

import de.htwg.se.vierGewinnt.controller.GameStatus
import de.htwg.se.vierGewinnt.controller.GameStatus.GameStatus


case class StateWon(var gameStatus: GameStatus) extends StateLike {
  override def handle(gamestate: Gamestate): Unit = {
    gamestate.myState = StateWon(GameStatus.IDLE)
  }
}