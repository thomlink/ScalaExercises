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

//import com.tomlinker.Checkout.{order, orders_formatted, prices, prices_formatted}


object Checkout extends App {

  case class Deal(quantity: Int, price: BigDecimal)

  //Name must not contain spaces
  case class ItemPrice(unit_price: BigDecimal, deal: Option[Deal])

  //case class ItemOrder(name: String, quantity: Int)
  
  //val order =
  //  "A,A,B,B,B,B,C,C,D"
  val order_formatted = Map("A" -> 2, "B" -> 4, "C" -> 2, "D" -> 1)
  val prices_formatted = Map(
    "A" -> ItemPrice(BigDecimal(50), Some(Deal(3, BigDecimal(120)))),
    "B" -> ItemPrice(BigDecimal(30), Some(Deal(2, BigDecimal(45.55)))),
    "C" -> ItemPrice(BigDecimal(20), None),
    "D" -> ItemPrice(BigDecimal(15), None)
  )

  println(get_total_cost(order_formatted, prices_formatted))

  //println(prices_formatted)
  //println(orders_formatted)
  //println(get_item_cost(orders_formatted(0), prices_formatted));

 

  def get_total_cost(orders: Map[String, Int], prices: Map[String, ItemPrice]): BigDecimal =
    orders.map{ order => get_item_cost(order, prices)}.sum
  

  def get_item_cost(order: (String, Int), prices: Map[String, ItemPrice]): BigDecimal = {

    def helper(orderQuantity: Int, price: ItemPrice): BigDecimal = 
      price.deal.fold(orderQuantity * price.unit_price) ( deal => {
        val deal_cost = 
          get_deal_cost(order, deal)
        val non_deal_cost = 
          (orderQuantity % deal.quantity) * price.unit_price
        deal_cost + non_deal_cost
      })
    
    val (orderName, orderQuantity) = order

    val price =
      prices.get(orderName)

    price.fold(BigDecimal(0))(price => helper(orderQuantity, price))
    // \price -> f price
  }

  def get_deal_cost(order: (String, Int), deal: Deal): BigDecimal = {
    val (_, orderQuantity) = order

    val n_deals = orderQuantity / deal.quantity
    if (n_deals > 0) {
      n_deals * deal.price
    } else 0
  }

//  def format_prices(prices: List[String]): Map[String, ItemPrice] = {
//    prices.map(x => format_price(x)).toMap
//  }
//
//  def format_price(price: String): (String, ItemPrice) = {
//    val split_price =
//      price
//        .split(" ")
//        .toList
//    split_price.length match {
//      case x if (x > 4) =>
//        split_price(0) -> ItemPrice(split_price(1).to, Some(Deal(split_price(2).toInt, split_price(4).toBigDecimal)))
//      case _ =>
//        split_price(0) -> ItemPrice(split_price(1).toBigDecimal, None)
//
//    }
//  }

  // occurences of a substring in a string
  def occurrences(src: String, tgt: String): Int = {
    src
      .sliding(tgt.length)
      .count(window => window == tgt)
  }

//  def format_order(order: String): Map[String, Int] = {
//  // "A,B,C,D,E,F,A,B"
//    // (X -> 3, Y->5)
//    //def formatOneOrder()
//
//    def helper(order_list: List[String], seen: String, order_string: String): Map[String,Int] =
//
//      def createIndividualOrder(name: String, orderList: List[String]) : (String, Int) = {
//        val quantity = occurrences(name, orderList.toString())
//
//      }
//
//
////      order_list match {
////        case Nil =>
////
////        case x :: xs if (occurrences(seen, x) == 0) =>
////          ItemOrder(x, occurrences(order_string, x)) :: helper(xs, seen + x, order_string)
////        case _ :: xs =>
////          helper(xs, seen, order_string)
////      }
//
//    helper(order
//             .split(",")
//             .toList,
//           "",
//           order)
//  }


  //TODO convert BigDecimal to BigDecimal

}
