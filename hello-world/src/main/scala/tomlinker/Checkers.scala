



//Write code to create a checkerboard pattern with the words "black" and "white" standing in for colours. Display the new words in the console. When you have finished this exercise, it should look something like this:
//Allow for a variable number of checkers to be generated x by x. 
//Replace the white / black with coloured squares.



package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object checkers extends App{
	
	println("Enter board width and height (x * x): ")
	val x = readInt()
	println(checkerboard(x))
	
	
	def checkerboard(x: Int) : String = {
		def checkerboardRow(x: Int) : String = x match {
			case x if (x == 1) => " x\n"
			case y if (y % 2 == 0)  => " o " + checkerboardRow(x-1)
			case y if (y % 2 != 0)  => " x " + checkerboardRow(x-1)
		}
		def checkerboard_helper(max: Int, current: Int) : String = current match {
			case x if (x == max) => checkerboardRow(x)
			case _ => checkerboardRow(max) + checkerboard_helper(max, current + 1)
		}
		checkerboard_helper(x, 1)
	}
	
	
	
	
	
	
	
	

}

