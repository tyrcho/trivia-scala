package com.adaptionsoft.games.uglytrivia


class Game {
  val gameState = new GameState

  def registerPlayer(playerName: String): Unit =
    gameState.register(playerName)

  def playTurn(roll: Int): Unit = {
    println(s"${player} is the current player")
    println(s"They have rolled a $roll")
    if (gameState.player.inPenaltyBox) handlePenaltyBox(roll)
    else advance(roll)
  }

  def recordRightAnswer(): Unit = {
    if (!gameState.staysInPenalty) {
      println("Answer was correct!!!!")
      gameState.player.addCoin()
    }
    gameState.nextTurn()
  }

  def recordWrongAnswer(): Unit = {
    println("Question was incorrectly answered")
    println(player + " was sent to the penalty box")
    gameState.player.inPenaltyBox = true
    gameState.nextTurn()
  }


  private def handlePenaltyBox(roll: Int): Unit = {
    gameState.canGetOut = roll % 2 != 0
    if (gameState.canGetOut) {
      println(s"$player is getting out of the penalty box")
      advance(roll)
    }
    else println(s"$player is not getting out of the penalty box")
  }

  private def advance(roll: Int): Unit = {
    gameState.player.move(roll)
    println(s"The category is ${gameState.category}")
    println(gameState.questionBox.nextQuestion(gameState.category))
  }


  def gameOver: Boolean = gameState.gameOver

  private def player: String = gameState.player.name

}