package com.tomlinker

import scala.annotation.tailrec


object recursion extends App{
	
	//println(downToOne(20))


	val nums = (1 to 10 by 2)

	//foreach
	nums.foreach(num => println(num))

	//map
	val mapped = nums.map(num => println(num))

	//fold
	val folded = nums.fold("")(
	        (acc, num) => acc + num.toString + "\n"
	    )
	println(folded)

	//filter
	(1 to 20).filter(num => 
	num % 2 != 0 && num <= 10).foreach(println)






	def downToOne(current: Int) : String = {
		@tailrec
		def helper(current: Int, result: String) : String = {
			if (current < 1){
				result
			} else {
				helper(current - 1, result + current.toString + ", ")
			}
		}
		helper(current, "")
	}	
	







}
