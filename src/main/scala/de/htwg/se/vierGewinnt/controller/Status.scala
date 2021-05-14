package de.htwg.se.vierGewinnt.controller

object Status extends Enumeration {
  type Status = Value
  val IDLE, WON, PLAYERTURN = Value

  val map: Map[Status, String] = Map[Status, String](
    IDLE -> "",
    WON -> "Game over",
    PLAYERTURN -> "Player turn"
  )

  def getMessage(Status: Status): String = {
    map(Status)
  }
}