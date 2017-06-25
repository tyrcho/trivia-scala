package com.adaptionsoft.games.uglytrivia


class Game {
  val players = new Players
  val questionBox = new QuestionBox


  def registerPlayer(playerName: String) =
    players.register(playerName)


  def playTurn(roll: Int): Unit = {
    println(s"${players.currentPlayerName} is the current player")
    println(s"They have rolled a $roll")
    if (players.inPenaltyBox(players.currentPlayer)) handlePenaltyBox(roll)
    else movePlayer(roll)
  }

  def recordRightAnswer() {
    if (!players.staysInPenalty) {
      println("Answer was correct!!!!")
      players.increaseMoney()
    }
    players.nextPlayer()
  }


  def recordWrongAnswer() = {
    println("Question was incorrectly answered")
    println(players.currentPlayerName + " was sent to the penalty box")
    players.sendToPenaltyBox()
    players.nextPlayer()
  }


  private def handlePenaltyBox(roll: Int) = {
    players.canGetOut = roll % 2 != 0
    if (players.canGetOut) {
      println(s"${players.currentPlayerName} is getting out of the penalty box")
      movePlayer(roll)
    }
    else println(s"${players.currentPlayerName} is not getting out of the penalty box")
  }

  private def movePlayer(roll: Int) = {
    players.move(roll)
    println(s"${players.currentPlayerName}'s new location is ${players.currentPlayerLocation}")
    println(s"The category is $currentCategory")
    println(questionBox.nextQuestion(currentCategory))
  }


  def gameIsNotOver = players.gameIsNotOver

  private def currentCategory: String = questionBox.category(players.currentPlayerLocation)

}