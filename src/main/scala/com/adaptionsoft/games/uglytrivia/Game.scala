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

  def isPlayable: Boolean = (players.size >= 2)

  def registerPlayer(playerName: String): Boolean = {
    players.add(playerName)
    println(playerName + " was added")
    println("They are player number " + players.size)
    true
  }

  def playTurn(roll: Int): Unit = {
    println(players.get(currentPlayer) + " is the current player")
    println("They have rolled a " + roll)
    if (inPenaltyBox(currentPlayer)) handlePenaltyBox(roll)
    else movePlayer(roll)
  }

  private def handlePenaltyBox(roll: Int) = {
    isGettingOutOfPenaltyBox = roll % 2 != 0
    if (isGettingOutOfPenaltyBox) {
      println(players.get(currentPlayer) + " is getting out of the penalty box")
      movePlayer(roll)
    }
    else println(players.get(currentPlayer) + " is not getting out of the penalty box")
  }

  private def movePlayer(roll: Int) = {
    places(currentPlayer) = (places(currentPlayer) + roll) % 12
    println(players.get(currentPlayer) + "'s new location is " + places(currentPlayer))
    println("The category is " + currentCategory)
    askQuestion()
  }

  private def askQuestion() =
    println(questions(currentCategory).pop)


  private def currentCategory: String = places(currentPlayer) match {
    case 0 | 4 | 8 => "Pop"
    case 1 | 5 | 9 => "Science"
    case 2 | 6 | 10 => "Sports"
    case _ => "Rock"
  }

  def recordRightAnswer: Boolean =
    if (inPenaltyBox(currentPlayer) && !isGettingOutOfPenaltyBox) {
      nextPlayer
      true
    }
    else increaseMoney

  private def nextPlayer = currentPlayer = (currentPlayer + 1) % players.size

  private def increaseMoney = {
    println("Answer was correct!!!!")
    purses(currentPlayer) += 1
    println(players.get(currentPlayer) + " now has " + purses(currentPlayer) + " Gold Coins.")
    val winner: Boolean = gameIsNotOver
    nextPlayer
    winner
  }

  def recordWrongAnswer: Boolean = {
    println("Question was incorrectly answered")
    println(players.get(currentPlayer) + " was sent to the penalty box")
    inPenaltyBox(currentPlayer) = true
    nextPlayer
    true
  }

  private def gameIsNotOver: Boolean = purses(currentPlayer) != 6
}