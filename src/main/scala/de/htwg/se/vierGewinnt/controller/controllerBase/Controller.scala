package de.htwg.se.vierGewinnt.controller.controllerBase


import de.htwg.se.vierGewinnt.controller.GameStatus._
import de.htwg.se.vierGewinnt.controller.{CellChanged, ControllerInterface, GameStatus, GridSizeChanged, IdleEvent, WinEvent}
import de.htwg.se.vierGewinnt.model.GridInterface
import de.htwg.se.vierGewinnt.model.gridBase.Cell
import de.htwg.se.vierGewinnt.util.UndoManager

class Controller (var grid: GridInterface) extends ControllerInterface {

  var playerList = Array(true, false)
  var gameStatus: Gamestate = Gamestate(StateIdle(GameStatus.IDLE))
  private val undoManager = new UndoManager
  var gridrow = 5
  var gridcol = 6

  def createEmptyGrid(s: String): Unit = {
    s match {
      case "Small" =>{
        gridrow = 5
        gridcol = 6
      }
      case "Middle" => {
        gridrow = 9
        gridcol = 10
      }
      case "Huge" => {
        gridrow = 15
        gridcol = 16
      }
    }
    resetPlayerList()
    gameStatus = Gamestate(StateIdle(GameStatus.IDLE))
    publish(new GridSizeChanged(s))
  }

  def setBottomVal(column: Int): Unit = {
    val value = if (playerList(0)) {
      1
    } else {
      2
    }
    for (i <- grid.cells.row - 1 to 0 by -1) {
      if (grid.col(column).cells(i).equals(Cell(0))) {
        undoManager.doStep(new SetCommand(i,column, value, this))
        if (this.check4Win(i, column)) {
          gameStatus.changeState()
          publish(new WinEvent)
          return
        } else {
          this.changeTurn()
          publish(new CellChanged)
          return
        }
      }
    }
  }

  def check4Win(row: Int, col: Int): Boolean = {
    if (check4number(grid.col(col).getCells)) {
      true
    } else if (check4number(grid.row(row).getCells)) {
      true
    } else if (check4number(grid.left_dia(row, col).getCells)) {
      true
    } else if (check4number(grid.right_dia(row, col).getCells)) {
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

  def resetPlayerList():Unit= {
    playerList = Array(true,false)
  }

  def gridToString: String = grid.toString

  def undo: Unit = {
    undoManager.undoStep
    publish(new CellChanged)
  }

  def redo: Unit ={
    undoManager.redoStep
    publish(new CellChanged)
  }

  override def getGameStatus(): GameStatus = gameStatus.myState.gameStatus

  override def getGridRow: Int = gridrow

  override def getGridCol: Int = gridcol
}