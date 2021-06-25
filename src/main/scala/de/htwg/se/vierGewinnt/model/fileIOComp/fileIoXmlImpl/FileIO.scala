package de.htwg.se.vierGewinnt.model.fileIOComp.fileIoXmlImpl


import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.vierGewinnt.vierGewinntModule
import de.htwg.se.vierGewinnt.model.fileIOComp.FileIOInterface
import de.htwg.se.vierGewinnt.model.gridComp.GridInterface

import java.io._
import scala.xml.{Elem, PrettyPrinter}


class FileIO extends FileIOInterface {
  override def load: (GridInterface, Array[Boolean]) = {
    var grid: GridInterface = null
    val file = scala.xml.XML.loadFile("grid.xml")
    val sizeAtt = (file \\ "grid" \ "@size")
    val player1 = (file \\ "grid" \ "@player1").text.toBoolean
    val player2 = (file \\ "grid" \ "@player2").text.toBoolean
    val size = sizeAtt.text.toInt
    val injector = Guice.createInjector(new vierGewinntModule)

    size match {
      case 30 => grid = injector.instance[GridInterface](Names.named("Grid Small"))
      case 72 => grid = injector.instance[GridInterface](Names.named("Grid Middle"))
      case 210 => grid = injector.instance[GridInterface](Names.named("Grid Huge"))
      case _ => println("Error Size")
    }
    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: Int = cell.text.trim.toInt
      grid = grid.set(row, col, value)
    }
    (grid, Array(player1,player2))
  }

  override def save(grid: GridInterface, players: Array[Boolean]): Unit = {saveString(grid, players)}

  def saveString(interface: GridInterface, players: Array[Boolean]): Unit = {
    val printer = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120,4)
    val xml = prettyPrinter.format(toXml(interface, players))
    printer.write(xml)
    printer.close()
  }

  def toXml(interface: GridInterface, players: Array[Boolean]): Elem = {
    <grid  size={(interface.rows * interface.cols).toString} player1={players.apply(0).toString} player2={players.apply(1).toString}>
      {
      for {
        row <- 0 until interface.rows
        col <- 0 until interface.cols
      }
      yield cellToXml(interface, row, col)
      }
    </grid>
  }

  def cellToXml(grid: GridInterface, row: Int, col: Int): Elem = {
    <cell row={ row.toString } col={ col.toString }>
      { grid.cell(row, col).value }
    </cell>
  }
}