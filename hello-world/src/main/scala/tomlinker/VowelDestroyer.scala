//Set up an array of the following musical instruments:
//cello
//guitar
//violin
//double bass
//Write a program to remove the vowels. 
//Display the output on the console.
//hint: .filter .map

package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object voweldestroyer extends App{
	val instruments = List("cello", "guitar", "violin", "double bass")
	val instruments_array = Array("cello", "guitar", "violin", "double bass")
	
	
	println("Array: ")
	remove_vowels_array(instruments_array).foreach(println)
	println("List : " + remove_vowels(instruments))
	
	

	//println(remove_vowels(instruments))


	def remove_vowels_array(words: Array[String]) : Array[String] = {
		var returned = Array[String]()
		for (word <- words){
			returned = returned :+ word.filter(!"aeiou".contains(_))
		}
		returned
	}
	
	def remove_vowels(words: List[String]) : List[String] = words match {
		case Nil => Nil
		case x :: xs => x.filter(!"aeiou".contains(_)) :: remove_vowels(xs)
	}
	
	

}

