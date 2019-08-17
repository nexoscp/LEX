import scala.collection.immutable.NumericRange
import scala.collection.mutable.ListBuffer

package object regex {

  trait ExpressableAsNFA {
    def asNFA(endState: nfa.State):nfa.State
  }

  sealed trait Exp extends ExpressableAsNFA {
    def pack(): this.type
    def *(): Exp = ???
    def ?(): Exp = ???
  }

  //val range: NumericRange.Inclusive[Char] = '1' to '2'

  implicit def range(in:NumericRange.Inclusive[Char]): Exp = ???

  case class SeqOfString(value: String) extends Exp {
    override def pack() = this

    override def asNFA(endState: nfa.State): nfa.State =
      value.foldRight(endState)((c, state) => nfa.state(nfa.CharTransition(c, state)))
  }

  implicit def r(s: String) = SeqOfString(s)

  case class SeqOfChar(value: Char) extends Exp {
    override def pack() = this

    override def asNFA(endState: nfa.State): nfa.State =
       nfa.state(nfa.CharTransition(value, endState))
  }

  implicit def char(s: Char) = SeqOfChar(s)

  /**
    * @see https://docs.scala-lang.org/overviews/core/string-interpolation.html
    */
  implicit class R(val sc: StringContext) extends AnyVal {
    def exp(args: Any*) = {
      val strings = sc.parts.iterator
      val expressions = args.iterator
      val list = ListBuffer[String](strings.next)
      while(strings.hasNext) {
        list += expressions.next.toString
        list += strings.next
      }
      SeqOfString(/*list:_**/"")
    }
  }

  case class Or(exp: Exp*) extends Exp {
    override def pack() = this

    override def asNFA(endState: nfa.State): nfa.State =
      nfa.state(exp.map(e => nfa.EpsylonTransition(e.asNFA(endState))):_*)
  }

  implicit class OrOperatorDecorator(self: Exp) {
    def |(that: Exp) = Or(self, that)
  }

  case class SeqExp(childs: Exp*) extends Exp {
    override def pack() = this

    override def asNFA(endState: nfa.State): nfa.State = childs.foldRight(endState)((exp, state) => exp.asNFA(state))
  }

  implicit class SeqExpX(target: Exp) {
    def ~(that: Exp) = SeqExp(target, that)
  }
}
