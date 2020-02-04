package com.tomlinker


object Arrays extends App {
	val arr = Array(1, 3, 5, 7, 9, 12, 42)
 	val totalArray: Int => Option[Int] = arr.lift

 	println(checkArrayValue(totalArray, 99))
 	println(checkArrayValue(totalArray, 2 ))

 	
}

def checkArrayValue(array: Int => Option[Int], index: Int): String = {
 	   	array(index) match {
   			case Some(num) => s"Element $num found for index $index"
   			case None      => s"No element found for index $index"
   		}
 	}


