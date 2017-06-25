package com.adaptionsoft.games.uglytrivia


class Game {
  val players = new Players
  val questionBox = new QuestionBox


  def registerPlayer(playerName: String) =
    players.register(playerName)


  def playTurn(roll: Int): Unit = {
    println(s"${currentPlayerName} is the current player")
    println(s"They have rolled a $roll")
    if (players.currentPlayer.inPenaltyBox) handlePenaltyBox(roll)
    else movePlayer(roll)
  }

  def recordRightAnswer() {
    if (!players.staysInPenalty) {
      println("Answer was correct!!!!")
      players.currentPlayer.addCoin()
    }
    players.nextTurn()
  }


  def recordWrongAnswer() = {
    println("Question was incorrectly answered")
    println(currentPlayerName + " was sent to the penalty box")
    players.currentPlayer.inPenaltyBox = true
    players.nextTurn()
  }


  private def handlePenaltyBox(roll: Int) = {
    players.canGetOut = roll % 2 != 0
    if (players.canGetOut) {
      println(s"$currentPlayerName is getting out of the penalty box")
      movePlayer(roll)
    }
    else println(s"$currentPlayerName is not getting out of the penalty box")
  }

  private def currentPlayerName = {
    players.currentPlayer.name
  }

  private def movePlayer(roll: Int) = {
    players.currentPlayer.move(roll)
    println(s"The category is $currentCategory")
    println(questionBox.nextQuestion(currentCategory))
  }


  def gameIsNotOver = players.gameIsNotOver

  private def currentCategory: String = questionBox.category(players.currentPlayer.location)

}