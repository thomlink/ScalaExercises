


//Write code to create a checkerboard pattern with the words "black" and "white" standing in for colours. Display the new words in the console. When you have finished this exercise, it should look something like this:
//Allow for a variable number of checkers to be generated x by x. 
//Replace the white / black with coloured squares.


package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object Checkers extends App {

  println("Enter board width and height (x * x): ")
  val x = readInt()
  println(checkerboard(x))


  def checkerboard(x: Int): String = {
    def checkerboardRow(x: Int, start: String): String = (x, start) match {
      case (1, " o ") => " x \n"
      case (1, " x ") => " o \n"
      case (y, " o ") if (y % 2 == 0) => " o " + checkerboardRow(x - 1, start)
      case (y, " o ") => " x " + checkerboardRow(x - 1, start)
      case (y, " x ") if (y % 2 == 0) => " x " + checkerboardRow(x - 1, start)
      case (y, " x ") => " o " + checkerboardRow(x - 1, start)


    }

    def checkerboard_helper(max: Int, current: Int): String = current match {
      case x if (x == max) && (x % 2 == 0) => checkerboardRow(x, " o ")
      case x if (x == max) => checkerboardRow(x, " x ")
      case x if (x % 2 == 0) => checkerboardRow(max, " o ") + checkerboard_helper(max, current + 1)
      case _ => checkerboardRow(max, " x ") + checkerboard_helper(max, current + 1)
    }

    checkerboard_helper(x, 1)
  }


}

