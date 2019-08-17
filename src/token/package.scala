import scala.collection.mutable

package object token {
  case class Token(name: String, value: regex.Exp) {
    def asNFA(): nfa.State = value.asNFA(nfa.state(this))

    override def toString(): String = name + " -> " + value
  }

  case class Tokens() {
    val tokens : mutable.Map[String, Token] = new mutable.HashMap[String,Token]()
    def add(token: Token):Unit = tokens.put(token.name, token)

    def asNFA(): nfa.State =
      nfa.state(tokens.values.map(token => nfa.EpsylonTransition(token.asNFA())).toSeq:_*)
  }
}
