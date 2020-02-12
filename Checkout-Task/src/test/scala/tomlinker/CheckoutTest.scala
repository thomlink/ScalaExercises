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

  test("formatOrder test"){
    //format_order(order: String) : List[ItemOrder]

    val order = "A,D,C,D,A,B,C,A,D,C,B,A,C,A"
    val expected_output = List(ItemOrder("A", 5), ItemOrder("D", 3), ItemOrder("C", 4), ItemOrder("B", 2)  )

    assert(format_order(order) == expected_output)
  }

  test("occurrences test") {
    //occurrences(src: String, tgt: String): Int

    val a_string = "aabccdaabccdaabccdccdbbaccaab"

    assert(occurrences(a_string, "aab") == 4)
    assert(occurrences(a_string, "bc" ) == 3)
    assert(occurrences(a_string, "d") == 4)
  }

  test("formatPrice test") {
    //def format_price(price: String) : ItemPrice = {
    val price_1 = "A 40 4 for 100"
    val expected_output_1 = ItemPrice("A", 40, Some(Deal(4, 100)) )
    val price_2 = "Jeremy784 34.5"
    val expected_output_2 = ItemPrice("Jeremy784", 34.5, None)

    assert(format_price(price_1) == expected_output_1)
    assert(format_price(price_2) == expected_output_2)

  }

  test
  //def format_prices(prices: List[String]) : List[ItemPrice] = prices match {

  //def get_deal_cost(order: ItemOrder, deal: Deal) : Double = {

  //def get_item_price_from_list(item_name: String, prices: List[ItemPrice] ) : Option[ItemPrice] = prices match {

  //def get_item_cost(order: ItemOrder, prices: List[ItemPrice] ) : Double = {

  //def get_total_cost(orders: List[ItemOrder], prices: List[ItemPrice]) : Double = orders match {

}
