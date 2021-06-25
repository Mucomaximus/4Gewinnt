package de.htwg.se.vierGewinnt.model.gridComp

import de.htwg.se.vierGewinnt.model.gridComp.gridBase.{Cell, Field, Grid, Matrix}

trait GridInterface {
  val cells: Matrix[Cell]
  def rows:Int
  def cols:Int

  def size: Int

  def cell(row: Int, col: Int): Cell

  def set(row: Int, col: Int, value: Int): Grid

  def row(row: Int): Field

  def col(col: Int): Field

  def left_dia(row: Int, col: Int): Field

  def right_dia(row: Int, col: Int): Field
}

trait CellInterface {
  def value:Int
  def isSet:Boolean
  def set(value:Int): CellInterface
}