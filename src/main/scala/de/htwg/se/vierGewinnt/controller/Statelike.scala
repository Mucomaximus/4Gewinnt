package de.htwg.se.vierGewinnt.controller

import de.htwg.se.vierGewinnt.controller.Status.Status

trait Statelike {

  var gameStatus: Status
  def writeName(gamestate: State, state: String): Unit

}