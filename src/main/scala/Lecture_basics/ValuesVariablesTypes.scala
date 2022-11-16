package Lecture_basics

object ValuesVariablesTypes extends App{
  val x: Int=44
  print(x)

  //vals are immutable
  //  x=2 will cause an error
  //compiler can infer types

  val aString : String="hello"; val anotherString = "goodbye"

  val aBoolean: Boolean=false
  val aChar: Char='a'
  val anInt: Int= x
  val aShort: Short = 4353 //integer types represented in 2 bytes instead of 4, if too large compiler will complain
  val aLong: Long=3424456640654349L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  //variables
  var aVariable: Int = 4
  aVariable = 5 //side effects



}
