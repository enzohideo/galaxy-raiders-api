package galaxyraiders.ports

import galaxyraiders.core.score.Score

interface ScoreHistory {
  fun save(score: Score)
}
