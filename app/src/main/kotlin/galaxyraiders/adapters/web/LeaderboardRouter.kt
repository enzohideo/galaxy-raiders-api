package galaxyraiders.adapters.web

import galaxyraiders.adapters.score.Leaderboard
import galaxyraiders.core.score.Score
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.EndpointGroup
import io.javalin.http.Context

class LeaderboardRouter : Router {
  val leaderboard = Leaderboard()

  override val path = "/leaderboard"

  override val endpoints = EndpointGroup {
    get("/", ::getLeaderboard)
  }

  private fun getLeaderboard(ctx: Context) {
    this.leaderboard.load()
    val dto: List<Score> = this.leaderboard.scores
    ctx.json(dto)
  }
}
