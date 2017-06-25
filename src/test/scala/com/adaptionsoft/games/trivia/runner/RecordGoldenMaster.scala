package com.adaptionsoft.games.trivia.runner

import java.io.PrintStream
import java.nio.file.{Files, Paths}
import java.util.Random

object RecordGoldenMaster extends App {


  for (i <- 0 to 1000) {
    record(i)
    println(s"recorded $i")
  }

  def record(seed: Long) =
    Console.withOut(buildStream(s"target/expected$seed.txt")) {
      GameRunner.runGame(new Random(seed))
    }


  def buildStream(file: String) = {
    val path = Paths.get(file)
    Files.deleteIfExists(path)
    val f = Files.createFile(path)
    val writer = Files.newOutputStream(f)
    new PrintStream(writer)
  }

}
