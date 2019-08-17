package emitter

import dfa.DFA

package object scala {
  trait Scala extends Emitter {
    override def emit(dfa: DFA): Unit = ???
  }
}
