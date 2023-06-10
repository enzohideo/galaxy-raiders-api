package galaxyraiders.ports

import galaxyraiders.core.score.Score

interface ScoreArchiver {
  fun load()
  fun submit(score: Score)
}
