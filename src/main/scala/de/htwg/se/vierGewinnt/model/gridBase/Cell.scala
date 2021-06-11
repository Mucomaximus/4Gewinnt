package de.htwg.se.vierGewinnt.model.gridBase

case class Cell(value: Int) {

  def set(value: Int): Cell = Cell(value)

  def isSet: Boolean = {
    value != 0
  }

  override def toString: String = value.toString
}
