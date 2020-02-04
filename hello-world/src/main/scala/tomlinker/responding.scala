package com.tomlinker

import scala.io.StdIn._

object input2 extends App{
	val responses = Array(
		"today is going to be a great day",
		"tomorrow is going to be a bad day",
		"the day after tomorrow will never come",
		"the day after the day after tomorrow will be the rapture"
	)

	val line = readLine("Enter a number between 1 and " + responses.length + "\n")
	val index = Try( )
		line.toInt - 1

	println(responses(index.toInt - 1))
}