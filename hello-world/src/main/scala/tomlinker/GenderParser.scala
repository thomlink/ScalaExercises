package com.tomlinker

import scala.annotation.tailrec

object genderParser extends App{
	val artists = List(
		"Beyonce (f)",
	 	"David Bowie (m)",
	 	"Tom Walker (m)",
	 	"P!nk (f)",
	 	"Miley Cyrus (nb)",
	 	"Chris Martin (m)",
	 	"Jess Glynne (f)"
	)
	
	println("Male       : " + count_male(artists).toString)
	println("Female     : " + count_female(artists).toString)
	println("Non-Binary : " + count_nonbinary(artists).toString)

	// Write a program to count how many artists are male, female and non-binary. Display your answer on the console.
	// hint .contains
	
	
	
	def count_male( list: List[String]) : Int = list match {
		case Nil => 0
		case x :: xs if x.contains("(m)") => 1 + count_male(xs)
		case _ :: xs => count_male(xs)
	}
	def count_female( list: List[String]) : Int = list match {
		case Nil => 0
		case x :: xs if x.contains("(f)") => 1 + count_female(xs)
		case _ :: xs => count_female(xs)
	}
	def count_nonbinary( list: List[String]) : Int = list match {
		case Nil => 0
		case x :: xs if x.contains("(nb)") => 1 + count_nonbinary(xs)
		case _ :: xs => count_nonbinary(xs)
	}



}
