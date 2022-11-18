package Lecture_basics

object Expressions extends App{

  val x= 1+2 //Expression
  println(x)
  println(2+3*4)
  //Other math operators: + - * /
  //Bitwise operator: & | ^(exclusive or) << >> (left/right shift) >>> (right shift with zero extension)
  println(1==x)
  //relational operators: == != > >= < <=
  println(!(1==x))
  //logical operators: ! && ||

  var aVariable=2
  aVariable+=3 //also with -= *= /= (only with variables)
  println(aVariable)

  //Instructions (DO) vs Expressions (VALUE)
  //  - Instructions are executed (Java), expressions are evaluated (Scala)

  //If expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 //IF expression: it gives back a number rather than just do something like comparing
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  //loop
  var i = 0
  while (i<10){
    println(i)
    i+=1
  }
  //NEVER WRITE THIS AGAIN
  //while expression will also return unit

  //everything in scala is an expression

  val aWeirdValue = (aVariable=3) // Unit === void (An empty type)
  println(aWeirdValue) //() ->unit

  //Code blocks

  val aCodeBlock={
    val y = 2
    val z = y+1

    if (z>2) "hello" else "goodbye"
  }
  //fun fact: a Code block is an expression, its value equals to the last expression in the code block
  // values inside of the code blocks cannot be referred outside of the code block



/*
* Exercise1: Difference between "hello word" vs println("hello world")
*/
//One is a value of type string  the other is a side effect (expression return unit )

/*
* Exercise 2: what is the value of the following expression
*/

val someValue={
  2<3
}
println(someValue)//True

val someOtherValue = {
  if (someValue) 239 else 986
  42
}
println(someOtherValue) //42




}
