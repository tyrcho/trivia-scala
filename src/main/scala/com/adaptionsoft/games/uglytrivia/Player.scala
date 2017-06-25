package com.adaptionsoft.games.uglytrivia

class Player(val name: String,
             var location: Int = 0,
             var coins: Int = 0,
             var inPenaltyBox: Boolean = false) {

  def addCoin() = {
    coins += 1
    println(s"${name} now has ${coins} Gold Coins.")
  }


  def move(roll: Int) = {
    location = (location + roll) % 12
    println(s"$name's new location is $location")
  }

}
