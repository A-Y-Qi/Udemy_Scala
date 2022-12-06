package Lecture.part4pm

object BracelessSyntax{

  // if - expressions
  val anIfExpression = if (2>3) "bigger" else "smaller"

  //java style
  val anIfExpression_v2 =
    if (2 > 3){
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2>3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then // higher indentation than if part
      "bigger"
    else
      "smaller"

  val anIfExpression_v5 =
    if 2>3 then
      val result="bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // for conprehensions
  val aForComprehension = for {
    n <- List(1,2,3)
    s <- List("black", "white")
  } yield s"$n$s"

  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match{
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"
  }

  // scala 3
  val aPatternMatch_v2 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int =
    val partialResult=40
    partialResult + 2

  // class definition with significant indentation (same for traits, objects enums etc)
  class Animal: // compiler expects the body of Animal
    def eat(): Unit =
      println("I'm eating")

    def grow():Unit =
      println("I'm growing")

  // 3000 more lines fo code
  end Animal // the end token for if, match........

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")

  //indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces
  // do not mix tabs and spaces





  def main(args: Array[String]): Unit = {
    println(anIfExpression_v5)

  }


}
