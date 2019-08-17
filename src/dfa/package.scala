
package object dfa {
  class DFA() {
    def minimize():MinimalDFA = ???
  }
  class MinimalDFA() extends DFA

  case class State(foo:nfa.State*)
}
