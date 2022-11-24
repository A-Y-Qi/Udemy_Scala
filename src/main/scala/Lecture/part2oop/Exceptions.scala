package Lecture.part2oop

object Exceptions extends App{
  //throwing and catching exceptions

  val x: String = null
  //println(x.length) will crash with NPE

  //throw new NullPointerException -> throw an exception

  // 1. throwing exceptions
  //val aWeirdValue: String = throw new NullPointerException //=> This line will cause an error since the error is not caught

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try{
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a Runtime exception")
    case e: NullPointerException => println("Caught a Null Pointer exception")
    case e: Exception => println("Caught an exception")
  } finally {
    // Code that will get executed NO MATTER WHAT
    // optional
    // does not return the type of expression -> Only for side effects
    println("finally")
  }

  //println(potentialFail)

  // 3. how to define your own exceptions
  class myException extends Exception
  val exception = new myException

  try{
    throw exception
  }
  catch {
    case e: Exception => println("Found Error")
  }
  finally{
    print("111111")
  }

  /*
  1. Crash your program with an OutOfMemoryError
  2. Crash with SOError
  3. PocketCalculator Class
    - add(x,y)
    - subtract(x,y)
    - multiply (x,y)
    - divide (x,y)

  Throw
    - OverFlowException if add(x,y) exceeds Int.MAX_VALUE
    - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
    - MathCalculationException for division by 0
  */
  // OOM
  // val array = Array.ofDim(Int.MaxValue)

  //SO
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  class OverFlowException extends RuntimeException
  class UnderFlowException extends RuntimeException
  class MathCalculationException extends RuntimeException ("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
     val result = x+y
      if (x>0 && y>0 && result<0) throw new OverFlowException
      else if (x<0 && y<0 && result>0) throw new UnderFlowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x>0 && y<0 && result<0) throw new OverFlowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderFlowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverFlowException
      else if (x < 0 && y < 0 && result < 0) throw new OverFlowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderFlowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderFlowException
      else result
    }

    def divide (x: Int, y: Int): Int = {
      if (y==0) throw new MathCalculationException
      else x/y
    }
  }
  println(PocketCalculator.divide(10,0))











}
