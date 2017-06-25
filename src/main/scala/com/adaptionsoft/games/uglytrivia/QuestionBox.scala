package com.adaptionsoft.games.uglytrivia

class QuestionBox {
  def category(location: Int)=names(location % 4)

  def nextQuestion(currentCategory: String) = cards(currentCategory).pop

  val names = Seq("Pop", "Science", "Sports", "Rock")
  val cards = names
    .map(cat => cat ->
      collection.mutable.Stack.tabulate(50)(i => s"$cat Question $i"))
    .toMap

}
