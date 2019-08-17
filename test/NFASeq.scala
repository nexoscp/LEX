import regex._

object NFASeq extends App {
  val foo = regex.SeqOfString("abc")

  println(foo.asNFA(nfa.state(token.Token("test", foo))))
  println(token.Token("test", foo | "bar").asNFA())


}
