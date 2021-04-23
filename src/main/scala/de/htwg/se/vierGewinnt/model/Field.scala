package de.htwg.se.vierGewinnt.model

case class Field(cells: Vector[Cell]) {

  def getCell(index: Int): Cell = cells(index)

  def getCells: Vector[Cell] = cells

  def reverseD: Vector[Cell] = cells
}
