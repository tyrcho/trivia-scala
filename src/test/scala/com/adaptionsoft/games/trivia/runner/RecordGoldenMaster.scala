package com.adaptionsoft.games.trivia.runner

import java.util.Random

import com.adaptionsoft.games.trivia.runner.GoldenMaster._

object RecordGoldenMaster extends App {

  for (i <- 0 to 100) {
    record(i)
    println(s"recorded $i")
  }

  private def record(seed: Long) =
    Console.withOut(buildStream(filename(seed))) {
      GameRunner.runGame(new Random(seed))
    }
}
