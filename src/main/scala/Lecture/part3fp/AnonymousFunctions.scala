package Lecture.part3fp

object AnonymousFunctions extends App{
  // Lambda Function
  val doubler = (x:Int) => x*2

  //multiple prams in a lambda
  val adder = (a: Int, b: Int) => a+b

  // no params
  val justDoSomething: () => Int = () => 3

  //need to add ()
  println(justDoSomething) //Lecture.part3fp.AnonymousFunctions$$$Lambda$18/0x0000000800b6ec40@880ec60
  println(justDoSomething()) // 3

  //curly braces with lambdas
  val stringToInt = { (str: String) => str.toInt}

  //More syntactic sugar
  val niceIncrementer: Int => Int = _+1 //equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int ={ _+_ } //equivalent to a,b => a + b

  /*
  Exercise:
    -Rewrite the "Special" Adder as an anonymous Function"
  */

  val superAdd = (x: Int) => (y:Int) => x+y

  println(superAdd(3)(4))





}
