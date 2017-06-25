package com.adaptionsoft.games.uglytrivia

import java.util.ArrayList


class Game {
  var players: ArrayList[String] = new ArrayList[String]
  var places: Array[Int] = new Array[Int](6)
  var purses: Array[Int] = new Array[Int](6)
  var inPenaltyBox: Array[Boolean] = new Array[Boolean](6)
  var currentPlayer: Int = 0
  var isGettingOutOfPenaltyBox: Boolean = false

  val questions = Seq("Pop", "Science", "Sports", "Rock")
    .map(cat => cat ->
      collection.mutable.Stack.tabulate(50)(i => s"$cat Question $i"))
    .toMap


  def registerPlayer(playerName: String): Boolean = {
    players.add(playerName)
    println(playerName + " was added")
    println(s"They are player number ${players.size}")
    true
  }

  def playTurn(roll: Int): Unit = {
    println(s"$currentPlayerName is the current player")
    println(s"They have rolled a $roll")
    if (inPenaltyBox(currentPlayer)) handlePenaltyBox(roll)
    else movePlayer(roll)
  }

  private def handlePenaltyBox(roll: Int) = {
    isGettingOutOfPenaltyBox = roll % 2 != 0
    if (isGettingOutOfPenaltyBox) {
      println(s"$currentPlayerName is getting out of the penalty box")
      movePlayer(roll)
    }
    else println(s"$currentPlayerName is not getting out of the penalty box")
  }

  private def movePlayer(roll: Int) = {
    places(currentPlayer) = (currentPlayerLocation + roll) % 12
    println(s"$currentPlayerName's new location is $currentPlayerLocation")
    println(s"The category is $currentCategory")
    askQuestion()
  }



  private def currentCategory: String = places(currentPlayer) match {
    case 0 | 4 | 8 => "Pop"
    case 1 | 5 | 9 => "Science"
    case 2 | 6 | 10 => "Sports"
    case _ => "Rock"
  }

  def recordRightAnswer() =
    if (inPenaltyBox(currentPlayer) && !isGettingOutOfPenaltyBox)
      nextPlayer()
    else
      increaseMoney


  private def increaseMoney = {
    println("Answer was correct!!!!")
    purses(currentPlayer) += 1
    println(s"$currentPlayerName now has $currentPlayerMoney Gold Coins.")
    nextPlayer()
  }


  def recordWrongAnswer() = {
    println("Question was incorrectly answered")
    println(currentPlayerName + " was sent to the penalty box")
    inPenaltyBox(currentPlayer) = true
    nextPlayer()
  }

  private def currentPlayerLocation = places(currentPlayer)

  private def askQuestion() =
    println(questions(currentCategory).pop)

  private def nextPlayer() =
    currentPlayer = (currentPlayer + 1) % players.size

  def gameIsNotOver: Boolean = purses.forall(6 !=)

  private def currentPlayerMoney = purses(currentPlayer)

  private def currentPlayerName = players.get(currentPlayer)

  def isPlayable: Boolean = (players.size >= 2)

}