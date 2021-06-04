package de.htwg.se.vierGewinnt.controller.controllerBase

import de.htwg.se.connect_four.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.connect_four.controller.controllerComponent.GameStatus


case class StatelickWIN(var gameStatus: GameStatus) extends StateLike {
  override def handle(gamestate: Gamestate): Unit = {
    gamestate.mystate = StatelickWIN(GameStatus.IDLE)
  }
}