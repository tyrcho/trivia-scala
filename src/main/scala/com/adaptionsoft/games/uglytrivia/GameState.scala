package com.adaptionsoft.games.uglytrivia


class GameState {

  var players = Seq.empty[Player]
  var turn = 0
  var canGetOut = false
  val questionBox = new QuestionBox

  def register(playerName: String): Unit = {
    players :+= new Player(playerName)
    println(playerName + " was added")
    println(s"They are player number ${players.size}")
  }

  def nextTurn(): Unit = {
    turn += 1
  }


  def category: String =
    questionBox.category(player.location)

  def player: Player =
    players(turn % players.size)

  def staysInPenalty: Boolean =
    player.inPenaltyBox && !canGetOut

  def gameOver: Boolean =
    players.exists(_.hasWon)
}