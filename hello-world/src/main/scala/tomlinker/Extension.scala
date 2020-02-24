package com.tomlinker

object Extension extends App {

  println(diagonally("DIAGONALLY"))


  def diagonally(word: String): String = {
    def helper(word: String, pos: Int): String = {
      def makeRow(spaces: Int, letter: Char): String = {
        spaces match {
          case x if (x == 0) => letter.toString
          case _ => " " + makeRow(spaces - 1, letter)
        }
      }

      pos match {
        case x if x > word.length - 1 => ""
        case _ => makeRow(pos, word.charAt(pos)) + "\n" + helper(word, pos + 1)
      }
    }

    helper(word, 0)
  }
}