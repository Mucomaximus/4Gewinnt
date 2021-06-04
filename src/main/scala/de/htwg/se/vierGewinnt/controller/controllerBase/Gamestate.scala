package de.htwg.se.vierGewinnt.controller.controllerBase

case class Gamestate(var mystate:StateLike) {

  def changeState(): Unit = {
    mystate.handle(this)
  }
}