package com.tomlinker

import com.tomlinker.Checkout._

class CheckoutTest extends org.scalatest.FunSuite {
  println("winning")
  assert("foo" == "foo")

  test("check overall makeOrder()") {

    var order = "A,D,C,D,A,B,C,A,D,C,B,A,C,A"
    var prices = List("A 50 3 for 130","B 30 2 for 45","C 20","D 15")
    assert(makeOrder(order, prices) == 400.0)

    order = "A,A,A,A,A,A"
    assert( makeOrder(order, prices) == 260 )

    order = "A,A,B,B,C,C,D,D"
    prices = List("A 50 4 for 130","B 60 2 for 90","C 20 2 for 38","D 15 5 for 60")
    assert( makeOrder(order, prices) == 258 )

  }

  //def format_order(order: String) : List[ItemOrder] = {

  //def occurrences(src: String, tgt: String): Int = {

  //def format_price(price: String) : ItemPrice = {

  //def format_prices(prices: List[String]) : List[ItemPrice] = prices match {

  //def get_deal_cost(order: ItemOrder, deal: Deal) : Double = {

  //def get_item_price_from_list(item_name: String, prices: List[ItemPrice] ) : Option[ItemPrice] = prices match {

  //def get_item_cost(order: ItemOrder, prices: List[ItemPrice] ) : Double = {

  //def get_total_cost(orders: List[ItemOrder], prices: List[ItemPrice]) : Double = orders match {

}
