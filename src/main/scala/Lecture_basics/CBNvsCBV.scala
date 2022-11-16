package Lecture_basics

object CBNvsCBV extends App{
  def calledByValue(x: Long): Unit={
    println("by value "+x)
    println("by value "+x)
    /*
    This function is actually evaluated as:
      System.nanoTime()=6127442954723082L
      println("by value "+6127442954723082L)
      println("by value "+6127442954723082L)
    */
  }

  def calledByName(x: => Long):Unit={
    println("by name " + x)
    println("by name " + x)
    /*
        This function is actually evaluated as:
          println("by value "+System.nanoTime())
          println("by value "+System.nanoTime())

    The arrow sign delays the evaluation of the parameter until it is used
        */
  }
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int)= println(x)

  //printFirst(infinite(),34) This will result in crushing
  printFirst(34, infinite()) //This will not crush

}
