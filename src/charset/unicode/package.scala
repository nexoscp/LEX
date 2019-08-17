import charset.CharSet

package object unicode {
  /**
    * @see https://en.wikipedia.org/wiki/Unicode
    */
  trait Unicode extends CharSet

  case class Unicode11_0() extends Unicode {
    def maxNumberOfCharacters = 137439
  }
}
