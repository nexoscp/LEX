import nfa.{CharTransition, EpsylonTransition, State}
import org.scalatest._
import regex._

import scala.collection.mutable.ArrayBuffer

class RegexTest extends FlatSpec with Matchers {
  "regex 'a'" should "have only one transition with char 'a'" in {
    object SubjectUnderTest extends Grammer {
      "a" -> "a"
    }
    val nfa = SubjectUnderTest.asNFA()
    val all = ArrayBuffer.empty[Char]
    collect(nfa, all)
    all.size should be(1)
    all(0) should be('a')
  }

  "prodct a, b" should "have only two transition with chars 'a', 'b'" in {
    object SubjectUnderTest extends Grammer {
      "a" -> "a"
      "b" -> "b"
    }
    val nfa = SubjectUnderTest.asNFA()
    val all = ArrayBuffer.empty[Char]
    collect(nfa, all)
    all.size should be(2)
    all.contains('a') should be(true)
    all.contains('b') should be(true)
  }

  "regex or" should "have only two transition with chars 'a', 'b'" in {
    object SubjectUnderTest extends Grammer {
      "a" -> (exp"a" | "b")
    }
    val nfa = SubjectUnderTest.asNFA()
    val all = ArrayBuffer.empty[Char]
    collect(nfa, all)
    all.size should be(2)
    all.contains('a') should be(true)
    all.contains('b') should be(true)
  }

  "regex seq" should "have only two transition with chars 'a', 'b'" in {
    object SubjectUnderTest extends Grammer {
      "a" -> (exp"a" ~ "b")
    }
    val nfa = SubjectUnderTest.asNFA()
    val all = ArrayBuffer.empty[Char]
    collect(nfa, all)
    all.size should be(2)
    all.contains('a') should be(true)
    all.contains('b') should be(true)
  }
   def collect(s:State, all:ArrayBuffer[Char]):Unit = {
    s.transition.foreach {
      case EpsylonTransition(state) => collect(state, all)
      case CharTransition(char, state) => {
        all += char
        collect(state, all)
      }
    }
  }
}
