package com.adaptionsoft.games.trivia

class Game {
  val gameState = new GameState

  def registerPlayer(playerName: String): Unit =
    gameState.register(playerName)

  def playTurn(roll: Int): Unit = {
    println(s"$player is the current player")
    println(s"They have rolled a $roll")
    if (!gameState.player.inPenaltyBox || canGetOut(roll))
      drawQuestionCard(roll)
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


  private def canGetOut(roll: Int): Boolean = {
    gameState.canGetOut = roll % 2 != 0
    val isMaybe = if (gameState.canGetOut) "is" else "is not"
    println(s"$player $isMaybe getting out of the penalty box")
    gameState.canGetOut
  }

  private def drawQuestionCard(roll: Int): Unit = {
    gameState.player.move(roll)
    println(s"The category is ${gameState.category}")
    println(gameState.questionBox.drawNextCard(gameState.category))
  }


  def gameOver: Boolean = gameState.gameOver

  private def player: String = gameState.player.name

}