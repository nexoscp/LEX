import dfa.DFA

package object emitter {
  trait Emitter{
    def emit(dfa:DFA)
  }
}
