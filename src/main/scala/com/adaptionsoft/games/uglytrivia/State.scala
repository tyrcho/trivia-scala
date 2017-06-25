package com.adaptionsoft.games.uglytrivia

import java.util.ArrayList

class State {
  def currentPlayerLocation = places(currentPlayer)

  def currentPlayerName = players.get(currentPlayer)

  def currentPlayerMoney = purses(currentPlayer)


  var players: ArrayList[String] = new ArrayList[String]
  var places: Array[Int] = new Array[Int](6)
  var purses: Array[Int] = new Array[Int](6)
  var inPenaltyBox: Array[Boolean] = new Array[Boolean](6)
  var currentPlayer: Int = 0
  var isGettingOutOfPenaltyBox: Boolean = false

  def currentPlayerInPenaltyBox = inPenaltyBox(currentPlayer)

  def increaseMoney() = purses(currentPlayer) += 1

  def nextPlayer() =
    currentPlayer = (currentPlayer + 1) % players.size

  def gameIsNotOver = purses.forall(6 !=)
}
