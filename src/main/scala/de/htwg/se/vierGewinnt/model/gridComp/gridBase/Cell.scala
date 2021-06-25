package de.htwg.se.vierGewinnt.model.gridComp.gridBase

case class Cell(value: Int) {

  def set(value: Int): Cell = Cell(value)

  def isSet: Boolean = {
    value != 0
  }

  override def toString: String = value.toString
}

object Cell {
  import play.api.libs.json._
  implicit val cellWrites: OWrites[Cell] = Json.writes[Cell]
  implicit val cellReads: Reads[Cell] = Json.reads[Cell]
}