package com.tomlinker


// Lots of impurity ew
object Looping extends App {
  println(whileLoop())
  println(doWhileLoop())
  // forLoop1
  println(forLoop2)


  def whileLoop(): Int = {
    var i = 1
    var count = 0
    while (i < 1000) {
      if (i % 3 == 0) count = count + 1
      i = i + 1
    }
    count
  }

  def doWhileLoop(): Int = {
    var i = 1
    var count = 0
    do {
      if (i % 3 == 0) count = count + 1
      i = i + 1
    } while (i < 1000)
    count
  }

  // def forLoop1(){
  // 	for (i <- Array(1, 3, 5, 7, 9)) {
  // 		println(i)
  // 	}
  // }

  def forLoop2(): Int = {
    var count = 0
    for (i <- (1 to 1000)) {
      if (i % 3 == 0) count = count + 1
    }
    count

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







