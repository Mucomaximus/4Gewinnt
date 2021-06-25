package de.htwg.se.vierGewinnt.model.fileIOComp.fileIoJsonImpl

import java.io.{File, PrintWriter}
import scala.io.Source
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.vierGewinnt.vierGewinntModule
import de.htwg.se.vierGewinnt.model.fileIOComp.FileIOInterface
import de.htwg.se.vierGewinnt.model.gridComp.{CellInterface, GridInterface}
import play.api.libs.json.JsObject.writes
import play.api.libs.json._

class FileIO extends FileIOInterface {
  override def load: (GridInterface, Array[Boolean]) = {
    var grid :GridInterface = null
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "grid" \ "size").get.toString().toInt
    val rows = (json \ "grid" \ "rows").get.toString.toInt
    val cols = (json \ "grid" \ "cols").get.toString.toInt
    val player1 = (json \ "players" \ "Player1").get.toString().toBoolean
    val player2 = (json \ "players" \ "Player2").get.toString().toBoolean
    val injector = Guice.createInjector(new vierGewinntModule)

    size match {
      case 30 => grid = injector.instance[GridInterface](Names.named("Grid Small"))
      case 72 => grid = injector.instance[GridInterface](Names.named("Grid Middle"))
      case 210 => grid = injector.instance[GridInterface](Names.named("Grid Huge"))
      case _ => println("Error Size")
    }

    for (index <- 0 until rows * cols) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index)
      val value = (cell \ "value").as[Int]
      grid = grid.set(row,col,value)

    }
    (grid, Array(player1,player2))
  }

  override def save(grid: GridInterface, playerlist: Array[Boolean]): Unit = {
    val printer = new PrintWriter(new File("grid.json"))
    printer.write(Json.prettyPrint(toJson(grid, playerlist)))
    printer.close()
  }

  implicit val cellWrites: Writes[CellInterface] = new Writes[CellInterface] {
    override def writes(value: CellInterface): JsValue = {
      Json.obj(
        "value" -> value.value
      )
    }
  }

  def toJson(interface: GridInterface, playerlist: Array[Boolean]): JsObject = {
    Json.obj(
      "players" -> Json.obj(
        "Player1"->playerlist.apply(0),
        "Player2"->playerlist.apply(1),
      ),
      "grid" -> Json.obj(
        "size" -> JsNumber(interface.rows * interface.cols),
        "rows"->JsNumber(interface.rows),
        "cols"->JsNumber(interface.cols),
        "cells" -> Json.toJson(
          for {
            row <- 0 until interface.rows;
            col <- 0 until interface.cols
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(interface.cell(row, col))
            )
          }
        )
      )
    )
  }
}