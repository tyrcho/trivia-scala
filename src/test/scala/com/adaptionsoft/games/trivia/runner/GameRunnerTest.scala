package com.adaptionsoft.games.trivia.runner

import java.nio.file.Files
import java.util.Random

import com.adaptionsoft.games.trivia.runner.RecordGoldenMaster._
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.{FlatSpec, Matchers}

class GameRunnerTest extends FlatSpec with Matchers {

  behavior of "GameRunner"

  val seeds = Table("seed", 1 to 100: _*)

  forAll(seeds) { seed =>
    it should s"have same output for seed $seed" in {
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
