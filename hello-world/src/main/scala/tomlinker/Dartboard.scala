//For every number on a dartboard (1 to 20), work out the possible single, double and treble scores. (For example, the number 20 has the possible score 20, 40 and 60.)
//"\t" outputs the tab character to the console.
//Bonus points for a more functional approach
//Output
//1    2    3
//2    4    6
//...
//20    40    60


package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object dartboard extends App{
	
	
	println(dartboardScores())
	
	def dartboardScores() : String = {
		def helper(current: Int) : String = current match {
			case x if x > 20 => ""
			case x => x.toString + "\t" + (x*2).toString + "\t" + (x*3).toString + "\n" + helper(current + 1)
		}
		helper(1)
	}
	

}

