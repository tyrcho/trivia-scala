package com.adaptionsoft.games.trivia.runner

import java.util.Random

import com.adaptionsoft.games.uglytrivia.Game


object GameRunner extends App {

  runGame(new Random)

  def runGame(rand: Random) = {
    val aGame = new Game
    aGame.registerPlayer("Chet")
    aGame.registerPlayer("Pat")
    aGame.registerPlayer("Sue")

    do {
      aGame.playTurn(rand.nextInt(5) + 1)
      if (rand.nextInt(9) == 7) aGame.recordWrongAnswer
      else aGame.recordRightAnswer
    } while (aGame.gameIsNotOver)
  }
}