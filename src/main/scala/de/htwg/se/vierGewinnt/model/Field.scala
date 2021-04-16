package de.htwg.se.vierGewinnt.model

case class Field(cells:Vector[Cell]) {
  def cell(index:Int):Cell=cells(index)
  def getCells: Vector[Cell] = cells
}
