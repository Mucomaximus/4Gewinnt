package de.htwg.se.vierGewinnt.controller.controllerBase

import de.htwg.se.vierGewinnt.controller.GameStatus.GameStatus


trait StateLike {
  var gameStatus:GameStatus
  def handle(gamestate: Gamestate): Unit
}
