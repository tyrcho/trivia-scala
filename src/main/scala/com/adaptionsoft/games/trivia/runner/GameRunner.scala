package com.adaptionsoft.games.trivia.runner

import java.util.Random

import com.adaptionsoft.games.trivia.Game


object GameRunner extends App {

  runGame(new Random)

  def runGame(rand: Random) = {
    val game = new Game
    game.registerPlayer("Chet")
    game.registerPlayer("Pat")
    game.registerPlayer("Sue")

    def dice5sides() = rand.nextInt(5) + 1

    do {
      game.playTurn(dice5sides())
      val correct = rand.nextInt(9) != 7
      if (correct) game.recordRightAnswer()
      else game.recordWrongAnswer()
    } while (!game.gameOver)
  }
}