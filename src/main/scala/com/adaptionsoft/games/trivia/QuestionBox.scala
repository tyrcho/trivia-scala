package com.adaptionsoft.games.trivia

import scala.collection.mutable.Stack

class QuestionBox {

  val categories = Seq("Pop", "Science", "Sports", "Rock")

  val cardBoxes: Map[String, Stack[String]] =
    categories
      .map(cat => cat -> initCardBox(cat))
      .toMap


  def category(location: Int): String =
    categories(location % 4)

  def drawNextCard(currentCategory: String): String =
    cardBoxes(currentCategory).pop()

  private def initCardBox(cat: String) =
    Stack.tabulate(50)(i =>
      s"$cat Question $i")

}
