package de.htwg.se.vierGewinnt.controller

import de.htwg.se.vierGewinnt.controller.Status.Status

case class StateWon(var gameStatus: Status) extends Statelike {

  override def writeName(gamestate: State, state: String): Unit = {
    gamestate.setState(StateWon(Status.IDLE))
  }
}

