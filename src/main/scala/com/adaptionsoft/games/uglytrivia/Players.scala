package com.adaptionsoft.games.uglytrivia


class Players {
  var players = Seq.empty[Player]
  var turn = 0
  var canGetOut = false

  def register(playerName: String) = {
    players :+= new Player(playerName)
    println(playerName + " was added")
    println(s"They are player number ${players.size}")
  }

  def currentPlayer = players(turn % players.size)

  def nextTurn() = {
    turn += 1
  }

  def staysInPenalty: Boolean = currentPlayer.inPenaltyBox && !canGetOut

  def gameIsNotOver = players.forall(_.coins != 6)
}
