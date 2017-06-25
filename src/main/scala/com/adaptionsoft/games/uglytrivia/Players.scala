package com.adaptionsoft.games.uglytrivia

import java.util.ArrayList

class Players {


  var names: ArrayList[String] = new ArrayList[String]
  var places: Array[Int] = new Array[Int](6)
  var purses: Array[Int] = new Array[Int](6)
  var inPenaltyBox: Array[Boolean] = new Array[Boolean](6)
  var currentPlayer: Int = 0
  var canGetOut: Boolean = false


  def staysInPenalty: Boolean = inPenaltyBox(currentPlayer) && ! canGetOut

  def register(playerName: String) = {
    names.add(playerName)
    println(playerName + " was added")
    println(s"They are player number ${names.size}")

  }

  def move(roll: Int) =  {
    places(currentPlayer) = (currentPlayerLocation + roll) % 12
  }

  def sendToPenaltyBox() = inPenaltyBox(currentPlayer) = true

  def currentPlayerLocation = places(currentPlayer)

  def currentPlayerName = names.get(currentPlayer)

  def currentPlayerMoney = purses(currentPlayer)


  def increaseMoney() = {
    purses(currentPlayer) += 1
    println(s"$currentPlayerName now has $currentPlayerMoney Gold Coins.")
  }

  def nextPlayer() =
    currentPlayer = (currentPlayer + 1) % names.size

  def gameIsNotOver = purses.forall(6 !=)
}
