package de.htwg.se.vierGewinnt.model

import de.htwg.se.vierGewinnt.model.playerBase.Player
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec


class PlayerTest extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" must {
      val player = Player("Your Name")
      "have a name" in {
        player.name must  be("Your Name")
      }
      "have a nice String representation" in {
        player.toString must be("Your Name")
      }
    }
  }
}
