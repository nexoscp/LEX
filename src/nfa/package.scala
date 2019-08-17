import token.Token

package object nfa {

  trait TerminalState {
    this: State =>
    def token: Token
    def asDFA(): dfa.State = {
      dfa.State(this)
    }
  }

  trait State  {
    def transition: Seq[Transition]
    def asDFA(): dfa.State
  }

  def state(t: Transition*) : State = ANormalState(t)
  def state(k: Token, t: Transition*) : State with TerminalState = ANormalTerminalState(t, k)

  case class ANormalState(transition: Seq[Transition]) extends State {
    override def asDFA(): dfa.State = ???
  }

  case class ANormalTerminalState(transition: Seq[Transition], token: Token) extends State with TerminalState

  sealed trait Transition {
    def state: State
  }
  case class CharTransition(char: Char, state: State) extends Transition
  case class EpsylonTransition(state: State) extends Transition
}
