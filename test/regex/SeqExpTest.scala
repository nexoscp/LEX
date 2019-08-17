package regex

import org.scalatest.{FlatSpec, Matchers}

class SeqExpTest extends FlatSpec with Matchers {
  "seq of one " should "work" in {
    (exp"a" ~ "b").asNFA(nfa.state())
  }
}
