package de.htwg.se.vierGewinnt.model

import scala.collection.mutable.ArrayBuffer

case class Grid(cells: Matrix[Cell]) {
  def this(row: Int, col: Int) = this(new Matrix[Cell](row, col, Cell(0)))

  val size: Int = cells.size

  def cell(row: Int, col: Int): Cell = cells.cell(row, col)
  def set(row: Int, col: Int, value: Int): Grid = copy(cells.replaceCell(row, col, Cell(value)))
  def row(row: Int): Field = Field(cells.rows(row))
  def col(col: Int): Field = Field(cells.rows.map(row => row(col)))

  def left_dia(row: Int, col: Int): Field = {
    var mrow = row
    var mcol = col
    val mvec = ArrayBuffer[Cell]()
    while (mrow < cells.row - 1 && mcol > 0) {
      mrow = mrow + 1
      mcol = mcol - 1
    }
    while (mrow >= 0 && mcol < cells.col) {
      mvec.append(cells.cell(mrow, mcol))
      mrow = mrow - 1
      mcol = mcol + 1
    }
    Field(mvec.toVector)
  }


  def right_dia(row: Int, col: Int): Field = {
    var mrow = row
    var mcol = col
    val mvec = ArrayBuffer[Cell]()
    while (mrow > 0 && mcol > 0) {
      mrow = mrow - 1
      mcol = mcol - 1
    }
    while (mrow < cells.row && mcol < cells.col) {
      mvec.append(cells.cell(mrow, mcol))
      mrow = mrow + 1
      mcol = mcol + 1
    }
    Field(mvec.toVector)
  }

  override def toString: String = cells.toString
}
