package de.htwg.se.vierGewinnt.controller
import de.htwg.se.vierGewinnt.util._
import de.htwg.se.vierGewinnt.model._

class Controller(var grid: Grid) extends Observable {

  var playerList = Array(true, false)

  def createEmptyGrid(row: Int, col: Int): Unit = {
    grid = new Grid(row, col)
    notifyObservers()
  }

  def setValueToBottom(column: Int): Int = {
    val value = if (playerList(0)) {
      1
    } else {
      2
    }
    if (column > grid.cells.col - 1) {
      notifyObservers()
      return -1
    }
    for (i <- grid.cells.row - 1 to 0 by -1) {
      if (grid.col(column).cell(i).equals(Cell(0))) {
        grid = grid.set(i, column, value)
        notifyObservers()
        return i
      }
    }
    -1
  }

  def check4Vier(grid: Grid, row: Int, col: Int): Boolean = {
    if (viererCheck(grid.col(col).getCells)) {
      true
    } else if (viererCheck(grid.row(row).getCells)) {
      true
    } else if (viererCheck(grid.left_dia(row, col).getCells)) {
      true
    } else if (viererCheck(grid.right_dia(row, col).getCells)) {
      true
    } else {
      false
    }
  }

  private def check4number(vector: Vector[Cell]): Boolean = {
    var counter = 0
    for (cell <- vector) {
      if (cell.equals(Cell(currentPlayer()))) {
        counter = counter + 1
        if (counter == 4) {
          return true
        }
      } else {
        counter = 0
      }
    }
    false
  }

  def getTurn(playerNumber: Int): Boolean = {
    playerList(playerNumber)
  }

  def changeTurn(): Unit = {
    playerList(0) = !playerList(0)
    playerList(1) = !playerList(1)
  }

  def currentPlayer(): Int = {
    if (playerList(0)) {
      return 1
    }
    2
  }

  def gridToString: String = grid.toString
}