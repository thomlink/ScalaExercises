//Set up a string variable to hold the following results:
//val results = "Manchester United 1 Chelsea 0, Arsenal 1 Manchester United 1, Manchester United 3 Fulham 1, Liverpool 2 Manchester United 1, Swansea 2 Manchester United 4"
//Write a programme to work out how many wins Manchester United had, how many games they drew, and how many Manchester United lost.
//Extend the programme to work out how many goals Manchester United scored and how many they conceded.
//Suppose a win gains you 3 points, a draw 1 point, and a loss no points. Have your program work out how many points in total Manchester United have acquired.

package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object FootballResults extends App{

	val results = "Manchester United 1 Chelsea 0, Arsenal 1 Manchester United 1, Manchester United 3 Fulham 1, Liverpool 2 Manchester United 1, Swansea 2 Manchester United 4, Manchester United 1 Chelsea 3, Chelsea 3 Manchester United 0, Chelsea 2 Manchester United 2"
	//val results = "Manchester United 1 Chelsea 1, Arsenal 1 Manchester United 1, Manchester United 3 Fulham 3, Liverpool 2 Manchester United 1, Swansea 2 Manchester United 4"


	val wins = MUwins(results)
	println(wins)

	def MUwins(results: String) : Int = {
		println(results.split(",").toList)
		MUwins_helper(results.split(",").toList)
	}


	def MUhome(result: String) : Boolean = {
		
		//println(result.indexOf("Manchester United"))
		result.indexOf("Manchester United") < 2
	}

	def homeGoals(result: String) : Int = {
		def helper(result: List[String]) : Int = result match{
			case Nil => 0
			case x :: xs if (x.forall(_.isDigit)) => x.toInt
			case x :: xs => helper(xs)
		}
		helper(result.split("").toList)
	}

	def awayGoals(result: String) : Int = {
		homeGoals(result.reverse)
	}

	def MUwins_helper(results: List[String]) : Int = results match {
		case Nil => 0
		case x :: xs if (MUhome(x)) && (homeGoals(x) > awayGoals(x)) => 1 + MUwins_helper(xs)
		case x :: xs if (MUhome(x)) && (homeGoals(x) < awayGoals(x)) => 0 + MUwins_helper(xs)	
		case x :: xs if (!MUhome(x)) && (homeGoals(x) > awayGoals(x)) => 0 + MUwins_helper(xs)
		case x :: xs if (!MUhome(x)) && (homeGoals(x) < awayGoals(x)) => 1 + MUwins_helper(xs)	
		case x :: xs => MUwins_helper(xs)


	}
	def scores(results: List[String]) : List[(Int, Int)] = results match { 
		case Nil => (0,0) :: Nil
		case x :: xs => (homeGoals(x), awayGoals(x)) :: scores(xs)
	}

}







