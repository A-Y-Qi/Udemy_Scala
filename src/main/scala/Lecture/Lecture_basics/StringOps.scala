package Lecture_basics

object StringOps extends App{
  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  print(str.startsWith("Hello"))
  println(str.replace(" ","-"))
  println(str.toLowerCase())
  println(str.length)
  println(str.reverse)
  println(str.take(2))

  val aNumberString="45"
  val aNumber=aNumberString.toInt
  println("a"+:aNumberString:+'z')

  //Scala specific: String interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting=s"Hello, my name is $name and I will be turning ${age+1} years old"
  println(greeting)
  println(anotherGreeting)

  //F-interpolators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute" //%2.2f specify the format:
                                                              // 2 characters minimum with 2 decimal precision
  //raw interpolator
  println(raw"This is a \n newline") //raw will ignore the \n
  val escaped = "This is a \n newline"
  println(raw"$escaped")// \n will be applied in injected string


}
