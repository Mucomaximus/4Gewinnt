package de.htwg.se.vierGewinnt.model.Field.Comp

import

object Color extends Enumeration {

  type Color = Value

  val RED = Value("red")
  val YELLOW = Value("yellow")
  val EMPTY = Value("empty")



  implicit val colorReads = EnumRead.enumReads(Color)

  def toEnum(string: String): Color = {
    string match {
      case "red" => Color.RED
      case "yellow" => Color.YELLOW
      case "empty" => Color.EMPTY
    }
  }


}

