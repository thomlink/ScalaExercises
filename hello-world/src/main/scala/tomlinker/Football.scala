//Set up a string variable to hold the following results:
//val results = "Manchester United 1 Chelsea 0, Arsenal 1 Manchester United 1, Manchester United 3 Fulham 1, Liverpool 2 Manchester United 1, Swansea 2 Manchester United 4"
//Write a programme to work out how many wins Manchester United had, how many games they drew, and how many Manchester United lost.
//Extend the programme to work out how many goals Manchester United scored and how many they conceded.
//Suppose a win gains you 3 points, a draw 1 point, and a loss no points. Have your program work out how many points in total Manchester United have acquired.

package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object Football extends App {

  val results = "Manchester United 1 Chelsea 0, Arsenal 1 Manchester United 1, Manchester United 3 Fulham 1, Liverpool 2 Manchester United 1, Swansea 2 Manchester United 4"

  val wins = MUwins(results)
  val defeats = MUdefeats(results)
  val draws = MUdraws(results)
  val goalsScored = MUgoalsScored(results)
  val goalsConceded = MUgoalsConceded(results)
  val points = (wins * 3) + draws

  println(results + "\n\n")
  println("Manchester United Wins           : " + wins.toString)
  println("Manchester United defeats        : " + defeats.toString)
  println("Manchester United Draws          : " + draws.toString)
  println("Manchester United Goals Scored   : " + goalsScored.toString)
  println("Manchester United Goals Conceded : " + goalsConceded.toString)
  println("Manchester United Points         : " + points.toString)

  def MUgoalsScored(results: String): Int = {
    def MUgoalsScored_helper(results: List[String]): Int = results match {
      case Nil => 0
      case x :: xs if (MUhome(x)) => homeGoals(x) + MUgoalsScored_helper(xs)
      case x :: xs if (!MUhome(x)) => awayGoals(x) + MUgoalsScored_helper(xs)
    }

    MUgoalsScored_helper(results.split(",").toList)
  }

  def MUgoalsConceded(results: String): Int = {
    def MUgoalsConceded_helper(results: List[String]): Int = results match {
      case Nil => 0
      case x :: xs if (!MUhome(x)) => homeGoals(x) + MUgoalsConceded_helper(xs)
      case x :: xs if (MUhome(x)) => awayGoals(x) + MUgoalsConceded_helper(xs)
    }

    MUgoalsConceded_helper(results.split(",").toList)
  }


  def MUwins(results: String): Int = {
    def MUwins_helper(results: List[String]): Int = results match {
      case Nil => 0
      case x :: xs if ((winner(x) == "h") && MUhome(x)) => 1 + MUwins_helper(xs)
      case x :: xs if ((winner(x) == "a") && !MUhome(x)) => 1 + MUwins_helper(xs)
      case x :: xs => MUwins_helper(xs)
    }

    MUwins_helper(results.split(",").toList)
  }

  def MUdefeats(results: String): Int = {
    def MUdefeats_helper(results: List[String]): Int = results match {
      case Nil => 0
      case x :: xs if ((winner(x) == "a") && MUhome(x)) => 1 + MUdefeats_helper(xs)
      case x :: xs if ((winner(x) == "h") && !MUhome(x)) => 1 + MUdefeats_helper(xs)
      case x :: xs => MUdefeats_helper(xs)
    }

    MUdefeats_helper(results.split(",").toList)
  }

  def MUdraws(results: String): Int = {
    def MUdraws_helper(results: List[String]): Int = results match {
      case Nil => 0
      case x :: xs if (winner(x) == "d") => 1 + MUdraws_helper(xs)
      case x :: xs => MUdraws_helper(xs)
    }

    MUdraws_helper(results.split(",").toList)
  }


  def MUhome(result: String): Boolean = {
    result.indexOf("Manchester United") < 2
  }

  def homeGoals(result: String): Int = {
    def helper(result: List[String]): Int = result match {
      case Nil => 0
      case x :: xs if (x.forall(_.isDigit)) => x.toInt
      case x :: xs => helper(xs)
    }

    helper(result.split("").toList)
  }

  def awayGoals(result: String): Int = {
    homeGoals(result.reverse)
  }

  def winner(result: String): String = result match {
    case x if (homeGoals(result) > awayGoals(result)) => "h"
    case x if (homeGoals(result) < awayGoals(result)) => "a"
    case  => "d"

  }

  def scores(results: List[String]): List[(Int, Int)] = results match {
    case Nil => Nil
    case x :: xs => (homeGoals(x), awayGoals(x)) :: scores(xs)
  }

}







