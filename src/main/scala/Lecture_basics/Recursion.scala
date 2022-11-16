package Lecture_basics

import Lecture_basics.Functions.aFactorial

import scala.annotation.tailrec

object Recursion extends App{
  def aFactorial(n: Int): Int = {
    if (n == 1) 1
    else {
      println("Computing factorial of "+n+"I first need factorial of "+(n-1))
      val result = n * aFactorial(n-1)
      println("Computed factorial of "+n)
      result
    }
  }

  aFactorial(10)
  //aFactorial(5000) This will result in stack overflow error

  def anotherFactorial (n:Int): BigInt = {
    @tailrec //make sure the recursion is the last expression
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) //TAIL RECURSION = use recursive call as the last expression

    factHelper(n, 1)
  }


//println(anotherFactorial(5000))

//when you need loops, use tail recursion

/*
Exercise 1: Concatenate a string in n times
*/

def aLongString(word:String,n:Int):String={
  @tailrec
  def helperString(n:Int, tempWord:String):String={
    if(n==0) tempWord
    else{
      helperString(n-1,tempWord+word)
    }
  }
  helperString(n,"")
}
aLongString("a",10)
//println(aLongString("a",10))

/*
Exercise 2: IsPrime function tail recursive
*/
def isPrime(n:Int): Boolean={
  @tailrec
  def helperPrime(d:Int, flag:Boolean): Boolean= {
    if (d == 1) flag
    else if (!flag) flag //save some time
    else {
      helperPrime(d - 1, (n % d != 0) && flag)
    }
  }
  helperPrime(n/2,true)
  }
isPrime(2003)
//println(isPrime(2003))

/*
Exercise 3: Fibonacci Function
*/

def findFibonacci(n:Int): Int ={
  @tailrec
  def helperFibonacci(n1:Int,accumulator1:Int,accumulator2:Int):Int= {
    if (n1==n) accumulator1+accumulator2
    else {
      helperFibonacci(n1+1,accumulator2,accumulator1+accumulator2)
    }
  }
  if (n==1 || n==2) 1
  else{ helperFibonacci(3,1,1)}
  }
//findFibonacci(8)
//println(findFibonacci(8))

}







