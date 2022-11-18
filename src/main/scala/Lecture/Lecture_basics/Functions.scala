package Lecture_basics

object Functions  extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  //function with no parameter
  /*Note:
  -functions with parentheses must be called with parentheses
  -functions without parentheses must ne called without parentheses
  */

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())

  def aParameterlessFunction2: Int = 52

  println(aParameterlessFunction2)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + " " + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  //when you need loops, use recursion

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)

  }
  /*
1. A greeting function (name,age) => "Hi, my name is $name, I am $age years old"
2. Factorial function 1*2*3*.............n
3. A Fibonacci function:
  f(1)=1
  f(2)=1
  f(3)=f(n-1)+f(n-2)
4. Tests if a number is prime

*/
  //Exercise 1

  def aGreeting(name: String, age: Int): String = {
    s"Hi, my name is $name, I am $age years old"
  }

  println(aGreeting("david", 10))


  //Exercise 2:
  def aFactorial(n: Int): Int = {
    if (n == 1) 1
    else n * aFactorial(n - 1)
  }

  println(aFactorial(4))

  //Exercise 3
  def aFibonacci(n: Int): Int = {
    if (n == 1 || n==2)  1
    else  aFibonacci(n - 1)+aFibonacci(n - 2)
  }

  println(aFibonacci(10))

  //Exercise 4
  def isPrime(n: Int): Boolean = {
    def aDivide(n: Int, b: Int): Boolean = {
      if (b==1) true
      else n%b!=0 && aDivide(n,b-1)
    }
    aDivide(n,n/2)
  }

  println(isPrime(37))



}
