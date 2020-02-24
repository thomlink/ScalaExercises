package com.tomlinker

import com.tomlinker.Checkout._

class CheckoutTest extends org.scalatest.FunSuite {
  println("winning")
  assert("foo" == "foo")

  test("check overall makeOrder") {

    var order = "A,D,C,D,A,B,C,A,D,C,B,A,C,A"
    var prices = List("A 50 3 for 130","B 30 2 for 45","C 20","D 15")
    assert(make_order(order, prices) == 400.0)

    order = "A,A,A,A,A,A"
    assert( make_order(order, prices) == 260 )

    order = "A,A,B,B,C,C,D,D"
    prices = List("A 50 4 for 130","B 60 2 for 90","C 20 2 for 38","D 15 5 for 60")
    assert( make_order(order, prices) == 258 )

  }

  test("format_order test"){
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

  test("format_price test") {
    // format_price(price: String) : ItemPrice
    val price_1 = "A 40 4 for 100"
    val expected_output_1 = ItemPrice("A", 40, Some(Deal(4, 100)) )
    val price_2 = "Jeremy784 34.5"
    val expected_output_2 = ItemPrice("Jeremy784", 34.5, None)

    assert(format_price(price_1) == expected_output_1)
    assert(format_price(price_2) == expected_output_2)

  }

  test("format_prices test"){
    // format_prices(prices: List[String]) : List[ItemPrice]
    val expected_output = List(ItemPrice("A", 40, Some(Deal(4, 100))) , ItemPrice("Jeremy784", 34.5, None))
    val prices = List("A 40 4 for 100", "Jeremy784 34.5")

    assert(format_prices(prices) == expected_output)
  }

  test("get_deal_cost test"){
    // get_deal_cost(order: ItemOrder, deal: Deal) : Double

    var order = ItemOrder("A", 7)
    var deal = Deal(6, 100)
    assert(get_deal_cost(order, deal) == 100)

    order = ItemOrder("A", 7)
    deal = Deal(3, 100)
    assert(get_deal_cost(order, deal) == 200)

    order = ItemOrder("A", 7)
    deal = Deal(8, 1000)
    assert(get_deal_cost(order, deal) == 0)
  }


  test("get_item_price_from_list test - acceptance and rejection" ) {
    // get_item_price_from_list(item_name: String, prices: List[ItemPrice] ) : Option[ItemPrice]
    val prices = List(
      ItemPrice("A", 50, Some(Deal(3, 140))) ,
      ItemPrice("B", 60, Some(Deal(2, 100))) ,
      ItemPrice("fgh", 10.5, None) ,
      ItemPrice("12345", 300.10, None) ,
      ItemPrice(";'sdf", 50, Some(Deal(2, 95)))
    )

    assert( get_item_price_from_list("A", prices) == Some(ItemPrice("A", 50, Some(Deal(3, 140)))))
    assert( get_item_price_from_list("B", prices) == Some(ItemPrice("B", 60, Some(Deal(2, 100)))))
    assert( get_item_price_from_list("fgh", prices) == Some(ItemPrice("fgh", 10.5, None)))
    assert( get_item_price_from_list("12345", prices) == Some(ItemPrice("12345", 300.10, None)))
    assert( get_item_price_from_list(";'sdf", prices) == Some(ItemPrice(";'sdf", 50, Some(Deal(2, 95)))))

    assert( get_item_price_from_list("D", prices) == None)
    assert( get_item_price_from_list("8YBIN", prices) == None)
  }

  test("get_item_cost test") {
    // get_item_cost(order: ItemOrder, prices: List[ItemPrice] ) : Double
    val prices = List(
      ItemPrice("A", 50, Some(Deal(3, 140))) ,
      ItemPrice("B", 60, Some(Deal(2, 100))) ,
      ItemPrice("fgh", 10.5, None) ,
      ItemPrice("12345", 300.10, None) ,
      ItemPrice(";'sdf", 50, Some(Deal(2, 95)))
    )

    var order = ItemOrder("A", 10)
    assert( get_item_cost(order, prices) == 470 )

    order = ItemOrder("B", 1)
    assert( get_item_cost(order, prices) == 60 )

    order = ItemOrder("12345", 3)
    assert( get_item_cost(order, prices) == 900.30 )

    order = ItemOrder(";'sdf", 5)
    assert( get_item_cost(order, prices) == 240 )


  }

  test("get_total_cost test") {
    // get_total_cost(orders: List[ItemOrder], prices: List[ItemPrice]) : Double
    val orders = List(
      ItemOrder("A", 10),
      ItemOrder("B", 1),
      ItemOrder("12345", 3),
      ItemOrder(";'sdf", 5),
    )

    val prices = List(
      ItemPrice("A", 50, Some(Deal(3, 140))) ,
      ItemPrice("B", 60, Some(Deal(2, 100))) ,
      ItemPrice("fgh", 10.5, None) ,
      ItemPrice("12345", 300.10, None) ,
      ItemPrice(";'sdf", 50, Some(Deal(2, 95)))
    )

    assert(get_total_cost(orders, prices) == 470 + 60 + 900.30 + 240)
  }

  test("round_price test"){
    //   round_price(n: Double) : Double
    val numbers = Array(123.333445, 120, 6, 5.6753, 676.4589)

    assert(   round_price(numbers(0)) ==  123.33 )
    assert(   round_price(numbers(1)) ==  120.00 )
    assert(   round_price(numbers(2)) ==  6.00)
    assert(   round_price(numbers(3)) ==  5.68 )
    assert(   round_price(numbers(4)) ==  676.46)
  }



}
