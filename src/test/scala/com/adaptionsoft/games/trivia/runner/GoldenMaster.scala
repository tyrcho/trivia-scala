package com.adaptionsoft.games.trivia.runner

import java.io.PrintStream
import java.nio.file.{Files, Path, Paths}

object GoldenMaster {
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
