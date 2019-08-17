import regex._

/**
  * will implement https://docs.oracle.com/javaee/7/api/javax/json/stream/JsonGenerator.html
  *
  * TestSuit from http://www.json.org/JSON_checker/
  * Grammer from http://www.json.org/
  *  JSON standards (RFC7159 http://www.ietf.org/rfc/rfc7159.txt ,
  *  ECMA-404 http://www.ecma-international.org/publications/standards/Ecma-404.htm
  *
  *  https://github.com/miloyip/nativejson-benchmark
  */
object JSON extends Grammer {
 /* val json_false = */ "json_false" -> "false"
  val json_false = exp"false"
  val json_true = exp"true"
  val json_null = exp"null"
  val json_string:Exp = ???
  val json_number:Exp = ???
  val member:Exp = json_string ~ name_separator ~ value
  val json_object = begin_object ~ ( member ~ ( value_separator ~ member )* ).? ~ end_object
  val json_array:Exp = ???
  val ws: Exp = (
    exp"x20" |              // Space
      literal("x09") |              // Horizontal tab
      literal("x0A") |             // Line feed or New line
      literal("x0D")                // Carriage return
    )*

  val begin_array     = ws ~ (char('[') ~ ws)  // [ left square bracket

  val begin_object    = ws ~ (char('{') ~ ws)  // { left curly bracket

  val end_array       = ws ~ (char(']') ~ ws)  // ] right square bracket

  val end_object      = ws ~ (char('}') ~ ws)  // } right curly bracket

  val name_separator  = ws ~ (char(':') ~ ws)  // : colon

  val value_separator = ws ~ (char(',') ~ ws)  // , comma

  val value = json_false | json_null | json_true | json_object | json_array | json_number | json_string

  val json = json_object | json_array
 
}
