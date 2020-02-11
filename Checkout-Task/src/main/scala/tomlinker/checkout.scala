//Implement the code for a supermarket checkout that calculates the total price of a number of items
//In a normal supermarket, things are identified using Stock Keeping Units, or SKUs
// In our store, we’ll use individual letters of the alphabet (A, B, C, and so on as the SKUs)
//Our goods are priced individually.
//In addition, some items are multi-priced: buy n of them, and they’ll cost you y
//For example, item ‘A’ might cost 50 pence individually, but this week we have a special offer:
	// buy three ‘A’s and they’ll cost you £1.30
//In fact this week’s prices are: 

//Item Unit Price Special Price 
//A 50 3 for 130 
//B 30 2 for 45 
//C 20 
//D 15 

//Our checkout accepts items in any order, 
	//so that if we scan a B, an A, and another B, we’ll recognise the two B’s and price them at 45
	// (for a total price so far of 95). 
//Because the pricing changes frequently, 
	// we need to be able to pass in a set of pricing rules each time we start handling a checkout transaction. 



package com.tomlinker

import scala.annotation.tailrec
import scala.io.StdIn._

object Checkout extends App{

	case class Deal(quantity: Int, price: Int)
	case class ItemPrice(name: String, unit_price: Int, deal: Option[Deal])
	case class ItemOrder(name: String, quantity: Int)



	val order = "A,B,C,D,A,B,C,A,D,C,B,A,C,A"
	val prices = List("A 50 3 for 130","B 30 2 for 45","C 20","D 15")

	val prices_formatted = format_prices(prices))
	val order_formatted = println(format_order(order))

	println(2/2)
	
	

	//def item_cost(item_quantity: Int, price: List[String]) = price.length match {
	//	case x if x < 3 => { //no deals on
	//		item_quantity
	//	}
	//	case _ => {
//
	//	}
	//	val deals = item_quantity/price()
	//	}

	//}



	def format_prices(prices: List[String]) : List[ItemPrice] = prices match {
		case Nil => Nil
		case x::xs => format_price(x) :: format_prices(xs)
	}

	def format_price(price: String) : ItemPrice = {
		val split_price = price.split(" ").toList
		split_price.length match {
			case x if (x > 4) 	=> ItemPrice(split_price(0), split_price(1).toInt, Some(Deal(split_price(2).toInt, split_price(4).toInt)) )
			case _ 				=> ItemPrice(split_price(0), split_price(1).toInt, None)
			
		}
	}

	// occurences of a substring in a string
	def occurrences(src: String, tgt: String): Int = {
  		src.sliding(tgt.length).count(window => window == tgt)
	}


	def format_order(order: String) : List[ItemOrder] = {
		def helper(order_list: List[String], seen: String, order_string: String) : List[ItemOrder] = order_list match {
  			case Nil 									=> Nil
  			case x::xs if (occurrences(seen, x) == 0) 	=> ItemOrder(x, occurrences(order_string, x)) :: helper(xs, seen + x, order_string)
  			case _::xs									=> helper(xs, seen, order_string)
  		}
		helper(order.split(",").toList, "", order )
	}



}



















