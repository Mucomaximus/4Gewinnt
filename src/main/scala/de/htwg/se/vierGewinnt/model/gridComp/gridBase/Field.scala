package de.htwg.se.vierGewinnt.model.gridComp.gridBase

case class Field(cells: Vector[Cell]) {

  def getCell(index: Int): Cell = cells(index)

  def getCells: Vector[Cell] = cells

  def reverseD: Vector[Cell] = cells.reverse
}
