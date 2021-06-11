package de.htwg.se.vierGewinnt.controller.controllerBase

case class Gamestate(var myState:StateLike) {

  def changeState(): Unit = {
    myState.handle(this)
  }
}