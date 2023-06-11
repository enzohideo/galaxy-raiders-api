@file:Suppress("MatchingDeclarationName")
package galaxyraiders

import galaxyraiders.adapters.BasicRandomGenerator
import galaxyraiders.adapters.score.LeaderboardArchiver
import galaxyraiders.adapters.score.ScoreboardArchiver
import galaxyraiders.adapters.tui.TextUserInterface
import galaxyraiders.adapters.web.WebUserInterface
import galaxyraiders.core.game.GameEngine
import kotlin.concurrent.thread
import kotlin.random.Random

object AppConfig {
  val config = Config("GR__APP__")

  val randomSeed = config.get<Int>("RANDOM_SEED")
  val operationMode = config.get<OperationMode>("OPERATION_MODE")
}

fun main() {
  val generator = BasicRandomGenerator(
    rng = Random(seed = AppConfig.randomSeed)
  )

  val ui = when (AppConfig.operationMode) {
    OperationMode.Text -> TextUserInterface()
    OperationMode.Web -> WebUserInterface()
  }

  val (controller, visualizer) = ui.build()

  val scoreboard = ScoreboardArchiver()
  scoreboard.load()

  val leaderboard = LeaderboardArchiver()
  leaderboard.load()

  val gameEngine = GameEngine(
    generator, controller, visualizer, scoreboard, leaderboard
  )

  thread { gameEngine.execute() }

  ui.start()
}
