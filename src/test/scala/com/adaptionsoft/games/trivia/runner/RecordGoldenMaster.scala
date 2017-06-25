package com.adaptionsoft.games.trivia.runner

import java.io.PrintStream
import java.nio.file.{Files, Paths}

object RecordGoldenMaster extends App{
  def record() = {
    setOut("expected.txt")
    GameRunner.main(Array())
  }

  record()



  private def setOut(file: String) = {
    val path = Paths.get(file)
    Files.deleteIfExists(path)
    val f = Files.createFile(path)
    val writer = Files.newOutputStream(f)
    System.setOut(new PrintStream(writer))
  }
}
