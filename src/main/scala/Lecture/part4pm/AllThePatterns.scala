package Lecture.part4pm

object AllThePatterns extends App{

  // 1- constants
  val x: Any = "Scala"
  val constants = x match{
    case 1 =>"A number"
    case "Scala" => "THE Scala"
    case true => "the Truth"
    case AllThePatterns => "A singleton Object"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match{
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match{
    case (1,1) =>
    case (something,2) => s"I've found $something"
  }


}
