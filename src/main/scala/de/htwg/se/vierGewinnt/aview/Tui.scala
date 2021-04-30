package de.htwg.se.vierGewinnt.aview

import de.htwg.se.vierGewinnt.model._

class Tui {
  var playerList = Array(true, false)

  def processInputLine(input: String, grid: Grid): (Grid, (Int, Int)) = {
    input match {
      case "q" => (grid, (0, 0))
      case "n klein" =>
        playerList = Array(true, false)
        (new Grid(4, 5), (0,0))
      case "n mitte" =>
        playerList = Array(true, false)
        (new Grid(9,10),(0,0))
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil =>
            var value = 0
            if (playerList(0)) {
              value = 1
            } else {
              value = 2
            }
            if (column.asDigit > grid.cells.col - 1) {
              println("Falsche Eingabe, wdh!")
              return (grid, (-1, -1))
            }
            val checkGrid = bodenCheck(grid, column.asDigit, value)
            if (checkGrid._2 == -1) {
              println("Spalte voll, wdh!")
              return (grid, (-1, -1))
            }
            (checkGrid._1, (checkGrid._2, column.asDigit))
          case _ =>
            println("Falsche Eingabe, wdh!")
            (grid, (-1, -1))
        }
    }
  }


  def bodenCheck(grid: Grid, column: Int, value: Int): (Grid, Int) = {
    for (i <- grid.cells.row - 1 to 0 by -1) {
      if (grid.col(column).getCell(i).equals(Cell(0))) {
        return (grid.set(i, column, value), i)
      }
    }
    (grid, -1)
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

  def viererCheck(vector: Vector[Cell]): Boolean = {
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
}