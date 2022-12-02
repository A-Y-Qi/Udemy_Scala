package Lecture.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2

  }
  println(doubler(2))

  // function types = Function1[A,B].....Function21[A,B,...V]
  val stringToIntConverter = new Function1[String, Int]{
    override def apply (string: String): Int = string.toInt
  }
  println(stringToIntConverter("3")+4)

  val adder : ((Int, Int) => Int) = new Function2[Int, Int, Int]{
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }
  // Function types Function2[A,B,R] === (A,B) => R
  println(adder(2,3))

  //ALL SCALA FUNCTIONS ARE OBJECTS
  /*
  1. a function which takes 2 strings and concatenates them
  2. transform the MyPredicate and MyTransformer into function types
  3. Define a function which takes an Int and returns another function which takes an int and returns an int
      - what's the type of this function
      - how to do it
  */

  /*
    1. a function which takes 2 strings and concatenates them
    */

  val concatenate: ((String, String) => String) = new Function2[String, String, String]{
    override def apply(s1: String, s2: String): String = s1+s2
  }

  println(concatenate("a", "b"))

  /*
   2. transform the MyPredicate and MyTransformer into function types
   */
  // In this case we do not need the traits MyPredicate and MyTransformer anymore since
  // in MyPredicate, the function type is: A => Boolean
  // in MyTransformer, the function type is: A => B

  abstract class MyList3[+A] {
    def head: A

    def tail: MyList3[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList3[B]

    override def toString: String = "[" + printElements + "]"

    def printElements: String

    //higher-order functions：
    // Receive functions as parameters or return other functions as a result
    def map[B](transformer: A => B): MyList3[B]
    def flapMap[B](transformer: A => MyList3[B]): MyList3[B]
    def filter(predicate: A => Boolean): MyList3[A]

    //concatenation
    def ++[B >: A](list: MyList3[B]): MyList3[B]

  }

  /*
    3. Define a function which takes an Int and returns another function which takes an int and returns an int
        - what's the type of this function
        - how to do it
    */
  val superAdder: Function1[Int, Function1[Int,Int]] = new Function1[Int,Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int]
    = new Function1[Int, Int] {override def apply(y: Int): Int = x+y}
  }

  //这样写也可以
  val superAdder2: Function1[Int, (Int => Int)] = new Function1[Int, (Int => Int)] {
    override def apply(x: Int): Function1[Int, Int]
    = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder2(3)
  val adder10 = superAdder2(10)

  println(adder3(4))
  println(adder10(4))


}

trait MyFunction[A,B]{
  def apply(element: A): B
}
