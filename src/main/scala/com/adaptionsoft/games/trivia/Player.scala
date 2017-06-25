package com.adaptionsoft.games.trivia

class Player(val name: String,
             var location: Int = 0,
             var coins: Int = 0,
             var inPenaltyBox: Boolean = false) {

  def hasWon: Boolean =
    coins == 6

  def addCoin(): Unit = {
    coins += 1
    println(s"${name} now has ${coins} Gold Coins.")
  }


  def move(roll: Int): Unit = {
    location = (location + roll) % 12
    println(s"$name's new location is $location")
  }

}
