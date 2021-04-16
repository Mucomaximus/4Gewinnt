package de.htwg.se.vierGewinnt.model.Field

trait FieldInterface {

  def sizeOfRows: Int

  def sizeOfCols: Int

  def cell(row: Int, col: Int): Cell

  def col(col: Int): Set

  def row(row: Int): Set

  def set(row: Int, col: Int, color: Color.Value, isSet: Boolean): Board

  def checkRow(row: Int, color: Color): Boolean

  def checkCols(col: Int, color: Color): Boolean

  def checkDiagonal(row: Int, col: Int, playerColor: Color): Boolean

  def getBoardAsString(matrix: Matrix[Cell]): String

  def getCells: Matrix[Cell]

}

trait CellInterface {
  def isSet: Boolean

  def color: Color.Value
}