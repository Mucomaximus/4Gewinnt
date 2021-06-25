package de.htwg.se.vierGewinnt

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.vierGewinnt.controller.ControllerInterface
import de.htwg.se.vierGewinnt.controller.controllerBase
import de.htwg.se.vierGewinnt.model.fileIOComp.FileIOInterface
import de.htwg.se.vierGewinnt.model.gridComp.GridInterface
import de.htwg.se.vierGewinnt.model.gridComp.gridAdv.Grid
import de.htwg.se.vierGewinnt.model.fileIOComp.fileIoXmlImpl.FileIO

class vierGewinntModule extends AbstractModule with ScalaModule {

  val defaultRows: Int = 5
  val defaultCols: Int = 6

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultRows")).to(defaultRows)
    bindConstant().annotatedWith(Names.named("DefaultCols")).to(defaultCols)
    bind[GridInterface].to[Grid]
    bind[ControllerInterface].to[controllerBase.Controller]
    bind[GridInterface].annotatedWithName("Grid Small").toInstance(new Grid(5, 6))
    bind[GridInterface].annotatedWithName("Grid Middle").toInstance(new Grid(8, 9))
    bind[GridInterface].annotatedWithName("Grid Huge").toInstance(new Grid(14, 15))


    bind[FileIOInterface].to[FileIO]
  }
}