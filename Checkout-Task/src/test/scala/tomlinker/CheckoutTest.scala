package com.tomlinker

import com.tomlinker.Checkout._


class CheckoutTest extends org.scalatest.FunSuite {


  test("occurrences test") {
    //occurrences(src: String, tgt: String): Int

    val a_string = "aabccdaabccdaabccdccdbbaccaab"

    assert(occurrences(a_string, "aab") == 4)
    assert(occurrences(a_string, "bc") == 3)
    assert(occurrences(a_string, "d") == 4)
  }

  test("get_deal_cost test") {
    // def get_deal_cost(order: (String, Int), deal: Deal): BigDecimal = {

    var order = ("A", 7)
    var deal  = Deal(6, 100)
    assert(get_deal_cost(order, deal) == 100)

    deal = Deal(3, 100)
    assert(get_deal_cost(order, deal) == 200)

    deal = Deal(8, 1000)
    assert(get_deal_cost(order, deal) == 0)
  }

  test("get_item_cost test") {
    // def get_item_cost(order: (String, Int), prices: Map[String, ItemPrice]): Cost = {
    val prices = Map(
      "A" -> ItemPrice(BigDecimal(50), Some(Deal(3, BigDecimal(120)))),
      "B" -> ItemPrice(BigDecimal(30), Some(Deal(2, BigDecimal(45.55)))),
      "C" -> ItemPrice(BigDecimal(20), None),
      "D" -> ItemPrice(BigDecimal(15), None)
    )

    var order = ("A", 10)
    assert(get_item_cost(order, prices) == Cost(410, 460) )

    order = ("B", 1)
    assert(get_item_cost(order, prices) == Cost(30, 60))

    order = ("C", 3)
    assert(get_item_cost(order, prices) == Cost(60, 120))

    order = ("D", 5)
    assert(get_item_cost(order, prices) == Cost(75, 150))

  }

  test("get_total_cost test") {
    // get_total_cost(orders: Map[String, Int], prices: Map[String, ItemPrice]): Cost = {
    val order = Map("A" -> 5, "B" -> 10, "C" -> 2)
    val prices = Map(
      "A" -> ItemPrice(BigDecimal(50), Some(Deal(3, BigDecimal(120)))),
      "B" -> ItemPrice(BigDecimal(30), Some(Deal(2, BigDecimal(45)))),
      "C" -> ItemPrice(BigDecimal(20), None),
      "D" -> ItemPrice(BigDecimal(15), None)
    )

    assert(get_total_cost(order, prices) == Cost(BigDecimal(220 + 225 + 40) , 120+2*100 + 5*45 + 40*2 ))
  }

}
