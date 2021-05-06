package de.htwg.se.vierGewinnt.controller
import de.htwg.se.vierGewinnt.util._
import de.htwg.se.vierGewinnt.model._

class Controller(var grid: Grid) extends Observable {

  var playerList = Array(true, false)

  def createEmptyGrid(row: Int, col: Int): Unit = {
    grid = new Grid(row, col)
    notifyObservers()
  }



  def check4Win(row: Int, col: Int): Boolean = {
    if (check4Num(grid.col(col).getCells)) {
      true
    } else if (check4Num(grid.row(row).getCells)) {
      true
    } else if (check4Num(grid.left_dia(row, col).getCells)) {
      true
    } else if (check4Num(grid.right_dia(row, col).getCells)) {
      true
    } else {
      false
    }
  }

  private def check4Num(vector: Vector[Cell]): Boolean = {
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

  def setBottomVal(column: Int): Int = {
    val value = currentPlayer()

    if (column > grid.cells.col - 1) {
      notifyObservers()
      return -1
    }
    for (i <- grid.cells.row - 1 to 0 by -1) {
      if (grid.col(column).cells(i).equals(Cell(0))) {
        grid = grid.set(i, column, value)
        notifyObservers()
        return i
      }
    }
    -1
  }

  def getTurn(pNumber: Int): Boolean = {
    playerList(pNumber)
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