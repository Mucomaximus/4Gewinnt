package de.htwg.se.vierGewinnt.model


trait GridInterface {
  val cells: Matrix[Cell]

  def size:Int

  def cell(row: Int, col: Int): Cell

  def set(row: Int, col: Int, value: Int): Grid

  def row(row: Int): Field

  def col(col: Int): Field

  def left_dia(row: Int, col: Int): Field

  def right_dia(row: Int, col: Int): Field
}
