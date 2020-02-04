package com.tomlinker

import scala.annotation.tailrec


object recursion extends App{
	
	println(downToOne(20))

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