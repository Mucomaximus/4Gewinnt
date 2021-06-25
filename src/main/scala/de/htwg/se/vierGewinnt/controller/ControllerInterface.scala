package de.htwg.se.vierGewinnt.controller

import de.htwg.se.vierGewinnt.controller.GameStatus.GameStatus
import de.htwg.se.vierGewinnt.model.gridComp.GridInterface

import scala.swing.event.Event
import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def createEmptyGrid(s: String): Unit
  def setBottomVal(col:Int): Unit
  def check4Win(row:Int, col:Int):Boolean
  def getTurn(playerNumber:Int):Boolean
  def changeTurn():Unit
  def currentPlayer():Int
  def gridToString:String
  def resetPlayerList():Unit
  def getGameStatus:GameStatus
  def undo():Unit
  def redo():Unit
  def getGridRow:Int
  def getGridCol:Int
  var grid: GridInterface
  def save:Unit
  def load:Unit
}

class CellChanged extends Event

class GridSizeChanged(size:String) extends Event

class WinEvent extends Event

class IdleEvent extends Event