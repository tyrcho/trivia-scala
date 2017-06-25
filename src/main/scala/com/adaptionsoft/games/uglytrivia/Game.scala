package com.adaptionsoft.games.uglytrivia


class Game {
  val state = new State


  val questions = Seq("Pop", "Science", "Sports", "Rock")
    .map(cat => cat ->
      collection.mutable.Stack.tabulate(50)(i => s"$cat Question $i"))
    .toMap


  def registerPlayer(playerName: String) = {
    state.players.add(playerName)
    println(playerName + " was added")
    println(s"They are player number ${state.players.size}")
  }

  def playTurn(roll: Int): Unit = {
    println(s"${state.currentPlayerName} is the current player")
    println(s"They have rolled a $roll")
    if (state.inPenaltyBox(state.currentPlayer)) handlePenaltyBox(roll)
    else movePlayer(roll)
  }

  private def handlePenaltyBox(roll: Int) = {
    state.isGettingOutOfPenaltyBox = roll % 2 != 0
    if (state.isGettingOutOfPenaltyBox) {
      println(s"${state.currentPlayerName} is getting out of the penalty box")
      movePlayer(roll)
    }
    else println(s"${state.currentPlayerName} is not getting out of the penalty box")
  }

  private def movePlayer(roll: Int) = {
    state.places(state.currentPlayer) = (state.currentPlayerLocation + roll) % 12
    println(s"${state.currentPlayerName}'s new location is ${state.currentPlayerLocation}")
    println(s"The category is $currentCategory")
    askQuestion()
  }


  private def currentCategory: String = state.places(state.currentPlayer) match {
    case 0 | 4 | 8 => "Pop"
    case 1 | 5 | 9 => "Science"
    case 2 | 6 | 10 => "Sports"
    case _ => "Rock"
  }

  def recordRightAnswer() = {
    if (state.currentPlayerInPenaltyBox && !state.isGettingOutOfPenaltyBox)
      state.nextPlayer()
    else
      increaseMoney
  }


  private def increaseMoney = {
    println("Answer was correct!!!!")
    state.increaseMoney()
    println(s"${state.currentPlayerName} now has ${state.currentPlayerMoney} Gold Coins.")
    state.nextPlayer()
  }


  def recordWrongAnswer() = {
    println("Question was incorrectly answered")
    println(state.currentPlayerName + " was sent to the penalty box")
    state.inPenaltyBox(state.currentPlayer) = true
    state.nextPlayer()
  }

  private def askQuestion() =
    println(questions(currentCategory).pop)

  def gameIsNotOver = state.gameIsNotOver

  def isPlayable: Boolean = (state.players.size >= 2)

}