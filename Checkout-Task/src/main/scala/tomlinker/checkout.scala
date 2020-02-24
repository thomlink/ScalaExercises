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

import com.tomlinker.Checkout.{order, orders_formatted, prices, prices_formatted}

import scala.annotation.tailrec
import scala.io.StdIn._

object Checkout extends App {

  case class Deal(quantity: Int, price: Double)

  //Name must not contain spaces
  case class ItemPrice(name: String, unit_price: Double, deal: Option[Deal])

  case class ItemOrder(name: String, quantity: Int)

  val order =
    "A,A,B,B,B,B,C,C,D"
  val prices = List("A 50 3 for 120", "B 30 2 for 45", "C 20", "D 15")

  val prices_formatted =
    format_prices(prices)
  val orders_formatted =
    format_order(order)

  println(prices_formatted)
  println(orders_formatted)
  println(get_item_cost(orders_formatted(0), prices_formatted));

  println(get_total_cost(orders_formatted, prices_formatted))

  def make_order(order: String, prices: List[String]): Double = {
    get_total_cost(format_order(order), format_prices(prices))
  }

  def get_total_cost(orders: List[ItemOrder], prices: List[ItemPrice]): Double =
    orders match {
      case Nil => 0
      case x :: xs =>
        get_item_cost(x, prices) + get_total_cost(xs, prices)
    }

  def get_item_cost(order: ItemOrder, prices: List[ItemPrice]): Double = {
    def helper(order: ItemOrder, price: ItemPrice): Double =
      price.deal match {
        case None =>
          round_price(order.quantity * price.unit_price)
        case Some(deal) => {
          println(order.quantity)
          println(deal.quantity)
          val deal_cost =
            get_deal_cost(order, deal)
          val non_deal_cost = (order.quantity % deal.quantity) * price.unit_price
          round_price(deal_cost + non_deal_cost)
        }
      }

    val price =
      get_item_price_from_list(order.name, prices)
    if (price == None) {
      0
    } else {
      helper(order, price.get)
    }

  }

  def get_item_price_from_list(item_name: String, prices: List[ItemPrice]): Option[ItemPrice] =
    prices match {
      case Nil =>
        None
      case x :: xs if (x.name == item_name) =>
        Some(x)
      case _ :: xs =>
        get_item_price_from_list(item_name, xs)
    }

  def get_deal_cost(order: ItemOrder, deal: Deal): Double = {
    val n_deals = order.quantity / deal.quantity
    if (n_deals > 0) {
      n_deals * deal.price
    } else 0
  }

  def format_prices(prices: List[String]): List[ItemPrice] =
    prices match {
      case Nil =>
        Nil
      case x :: xs =>
        format_price(x) :: format_prices(xs)
    }

  def format_price(price: String): ItemPrice = {
    val split_price =
      price
        .split(" ")
        .toList
    split_price.length match {
      case x if (x > 4) =>
        ItemPrice(split_price(0),
                  split_price(1).toDouble,
                  Some(Deal(split_price(2).toInt, split_price(4).toDouble)))
      case _ =>
        ItemPrice(split_price(0), split_price(1).toDouble, None)

    }
  }

  // occurences of a substring in a string
  def occurrences(src: String, tgt: String): Int = {
    src
      .sliding(tgt.length)
      .count(window => window == tgt)
  }

  def format_order(order: String): List[ItemOrder] = {
    def helper(order_list: List[String], seen: String, order_string: String): List[ItemOrder] =
      order_list match {
        case Nil =>
          Nil
        case x :: xs if (occurrences(seen, x) == 0) =>
          ItemOrder(x, occurrences(order_string, x)) :: helper(xs, seen + x, order_string)
        case _ :: xs =>
          helper(xs, seen, order_string)
      }

    helper(order
             .split(",")
             .toList,
           "",
           order)
  }

  def round_price(n: Double) = {
    val s = math pow (10, 2);
    (math round n * s) / s
  }

}
