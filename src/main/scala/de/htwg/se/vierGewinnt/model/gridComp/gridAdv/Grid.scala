package de.htwg.se.vierGewinnt.model.gridComp.gridAdv

import de.htwg.se.vierGewinnt.model.gridComp.gridBase.{Grid=>BaseGrid}
import com.google.inject.Inject
import com.google.inject.name.Named

class Grid @Inject() (@Named("DefaultRows")row:Int,@Named("DefaultCols")col:Int) extends BaseGrid(row,col) {

}