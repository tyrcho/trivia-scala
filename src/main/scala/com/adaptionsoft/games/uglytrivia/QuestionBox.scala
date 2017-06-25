package com.adaptionsoft.games.uglytrivia

import scala.collection.mutable.Stack

class QuestionBox {

  val categories = Seq("Pop", "Science", "Sports", "Rock")

  val cards = categories
    .map(cat =>
      cat -> Stack.tabulate(50)(i =>
        s"$cat Question $i"))
    .toMap

  def category(location: Int): String =
    categories(location % 4)

  def nextQuestion(currentCategory: String): String = {
    cards(currentCategory).pop
  }

}
