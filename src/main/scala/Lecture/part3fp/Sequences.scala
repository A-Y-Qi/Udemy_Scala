package Lecture.part3fp

import scala.util.Random

object Sequences extends App{

  /* Sequences: A very general interface for data structures that
    - have a well defined order
    - can be indexed
    Supports various operations:
    - apply, iterator, length, reverse for indexing and iterating
    - concatenation, appending, prepending
    - a lot of others: grouping, sorting, zipping, searching, slicing
  */
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse) //reverse the sequence
  println(aSequence(2)) // value at index 2 of the sequence
  println(aSequence ++ Seq(7,5,6))  //  concatenate two sequences
  println(aSequence.sorted) // sort a sequence

  //Ranges
  val aRange: Seq[Int] = 1 to 10 //inclusive
  val aRange2: Seq[Int] = 1 until 10 // does not include 10 at the end
  aRange.foreach(println)
  aRange2.foreach(println)

  (1 to 10).foreach(x=>println("Hello"))

  /*
  List: A LinearSeq immutable linked list
    - head, tail, isEmpty methods are fast: O(1)
    - most operations are O(n): length, reverse
  */
  val aList = List(1,2,3)
  val prepended = 42 +: aList //List(42,1,2,3)
  println(prepended)
  val appended = aList :+ 44
  println(appended)
  println(appended(2))

  val apple5 = List.fill(5)("apple")
  println(apple5)
  println(aList.mkString("-|-"))

  /*
  Array: equivalent of simple Java arrays which can be manually constructed with predefined lengths
    - can be mutated (updated in place)
    - are interoperatable with Java's T[] arrays
    - indexing is fast
  */
  val numbers=Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println) //Null Null Null

  //mutation
  numbers(2)=0  //syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers //implicit conversion
  println(numbersSeq) //WrappedArray

  /*
  Vector: The default implementation for immutable sequences
    - effectively constant indexed read and write O(log32(n))
    - fast element addition: append/prepend
    - implemented as a fixed-branched trie (branch factor 32)
    - good performance for large sizes
  */
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double ={
    val r = new Random
    val times = for{
      it <- 1 to maxRuns
    }yield{
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum*1.0/maxRuns //average time for each update
  }
  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  //  advantage: keeps reference to tail
  // disadvantage: updating an element in the middle takes long
  println(getWriteTime(numbersList))
  //  advantage: depth of the tree is small
  //  disadvantage: needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

















}
