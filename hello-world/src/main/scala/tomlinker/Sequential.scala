// Write a program to work out if a series of 5 digits are consecutive numbers. Assume the input digits are in a string with the following format:
//numbers: String = "10-9-8-7-6"
//Make sure your code works for the following sequence, as well:
//numbers: String = "1-2-3-4-5"
//Output: "10-9-8-7-6 : Are sequential"

//Following on, work out if a series of 5 integers contain the same number. So the sequence "10-9-8-7-7" would yield the answer:
//"10-9-8-7-7 : Pair of numbers found"


package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object Sequential extends App {

  val tru_numbers1 = "10-9-8-7-6"
  val tru_numbers2 = "1-2-3-4-5"
  val fls_numbers1 = "10-9-8-7-7"
  val fls_numbers2 = "1-3-2-4-5"

  println(sequential(tru_numbers1))
  println(sequential(tru_numbers2))
  println(sequential(fls_numbers1))
  println(sequential(fls_numbers2))


  def sequential(numbers: String): Boolean = {
    def helper(numbers: List[String]): Boolean = numbers match {
      case Nil => true
      case x :: Nil => true
      case x :: y :: ys if (x.toInt + 1 == y.toInt) => true && helper(y :: ys)
      case x :: y :: ys if (x.toInt - 1 == y.toInt) => true && helper(y :: ys)
      case _ => false
    }

    helper(numbers.split("-").toList)
  }

}	