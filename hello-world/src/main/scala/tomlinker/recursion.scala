package com.tomlinker

import scala.annotation.tailrec


object recursion extends App{
	
	//println(downToOne(20))


	val nums = (1 to 1000)
	var count = 0 // I hate that I had to do this But could't see another way
	//foreach
	nums.foreach(num => {
		if (num % 3 == 0) count = count + 1
	})

	println(s"ForEach - count of numbers divisible by 3 is $count")

	
	
	val mapped = nums.map(num => if (num % 3 == 0) {1} else {0} )
	val sum = mapped.count(_ == 1)
	println(s"Map - count of numbers divisible by 3 is $sum")		

	//fold
	val folded = nums.fold(0)(
	        (sum, num) => {
	        	if (num % 3 == 0) {
	        		sum + 1
	        	} else {
	        		sum
	        	}
	    	}
	    )
	println(s"Fold - count of numbers divisible by 3 is $folded")

	//filter
	val filtered = (1 to 1000).filter(num => num % 3 == 0 )
	println("Filter - count of numbers divisible by 3 is " + filtered.length.toString)





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
