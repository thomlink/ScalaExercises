package com.tomlinker


// Lots of impurity ew
object looping extends App {
	whileLoop()
	doWhileLoop()
	forLoop1
	forLoop2


	def whileLoop(){
		var i = 1
		while (i < 10){
			println(i)
			i = i + 2
		}
	}

	def doWhileLoop(){
		var i = 1
		do {
 			println(i)
 			i = i + 2
		} while(i < 10)
	}

	def forLoop1(){
		for (i <- Array(1, 3, 5, 7, 9)) {
			println(i)
		}
	}

	def forLoop2(){
		for (i <- (1 to 10)) {
			if (i % 2 != 0) println(i)
		}
		
	}

	def getUserInput(): Array[String] = {
 		var input = ""
 		var arr: Array[String] = Array()

 		while (input != ":e") {
   			input = scala.io.StdIn.readLine("Please enter a word, :e to end.")
   			if (input != ":e" && !input.isEmpty) arr = arr :+ input
 		}
 		arr
	}

}







