package com.adaptionsoft.games.trivia.runner

import java.io.PrintStream
import java.nio.file.{Files, Path, Paths}
import java.util.Random

object RecordGoldenMaster extends App {


  for (i <- 0 to 100) {
    record(i)
    println(s"recorded $i")
  }

  def record(seed: Long) =
    Console.withOut(buildStream(filename(seed))) {
      GameRunner.runGame(new Random(seed))
    }


  def filename(seed: Long) = {
    s"target/expected$seed.txt"
  }

  def buildStream(file: String): PrintStream = {
    val path = Paths.get(file)
    Files.deleteIfExists(path)
    val f = Files.createFile(path)
    buildStream(f)
  }

  def buildStream(f: Path): PrintStream = {
    val writer = Files.newOutputStream(f)
    new PrintStream(writer)
  }
}
