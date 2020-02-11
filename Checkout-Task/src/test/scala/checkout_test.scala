import org.scalatest.Assertions._

object MainTest extends App {

  // will pass
  assert("foo" == "foo")    

  // will fail  
  assertResult("foo") {
    "bar".toUpperCase
  }

}