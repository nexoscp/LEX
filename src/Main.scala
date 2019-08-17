import regex._
import token.{Token, Tokens}

object Main extends App {
}

/**
  * @see https://www.youtube.com/watch?v=yLbdw06tKPQ Cake Pattern: The Bakery from the Black Lagoon
  */
trait GrammerCake {
  this: Grammer =>

}

trait Grammer {
  private val products = Tokens()
  def literal(raw:String): String = raw

  implicit class S(name:String) {
    def ->(exp: Exp): Token = {
      val token = Token(name, exp)
      products add token
      token
    }
  }
    def asNFA(): nfa.State = products.asNFA()
}

