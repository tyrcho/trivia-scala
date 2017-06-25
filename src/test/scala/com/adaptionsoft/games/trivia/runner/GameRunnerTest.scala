package com.adaptionsoft.games.trivia.runner

import java.nio.file.Files
import java.util.Random

import com.adaptionsoft.games.trivia.runner.RecordGoldenMaster._
import org.scalatest.{FlatSpec, Matchers}

class GameRunnerTest extends FlatSpec with Matchers {


  "GameRunner" should "have same output" in {
    for (seed <- 0 to 100) {
      val tmp = Files.createTempFile("out", "txt")
      Console.withOut(buildStream(tmp)) {
        GameRunner.runGame(new Random(seed))
      }
      val expectedLines = io.Source.fromFile(filename(seed)).getLines.toVector
      val actualLines = io.Source.fromFile(tmp.toString).getLines.toVector
      actualLines shouldBe expectedLines
    }
  }
}
