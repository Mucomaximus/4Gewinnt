package de.htwg.se.vierGewinnt.controller

case class State(var mystate: Statelike) {

  def setState(statelike: Statelike):Unit = mystate = statelike

  def writeName(name: String): Unit = {
    mystate.writeName(this,name)
  }
}
