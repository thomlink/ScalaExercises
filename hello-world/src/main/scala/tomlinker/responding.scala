package com.tomlinker

import scala.io.StdIn._
import scala.util._

object responding extends App {
  val responses = Array(
    "today is going to be a great day",
    "tomorrow is going to be a bad day",
    "the day after tomorrow will never come",
    "the day after the day after tomorrow will be the rapture"
  )
  val totalResponses: Int => Option[String] = responses.lift


  println("Enter a number: ")
  val number = Try(readInt()) match {
    case Success(num) => {
      // num match {
      // 	case x if x > 3 => 3
      // 	case x if x < 0 => 0
      // 	case _ => num
      // }
      num
    }
    case Failure(e) => {
      println(s"Error: ${e.getMessage}, falling back to default value of 0")
      1
    }

  }

  totalResponses(number) match {
    case Some(response) => println(response)
    case None => println(s"No element found for index $number")
  }

}