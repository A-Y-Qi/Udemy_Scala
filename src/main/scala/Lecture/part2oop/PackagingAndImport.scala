package Lecture.part2oop

import Lecture.Lecture_basics.{PrinceCharmingForTest, CinderellaForTest as Princess}

import java.sql
// or Lecture.Lecture_basics._ ->import everything in the package

object PackagingAndImport extends App{

  // package members are accessible by their simple name
  val writer = new Writer ("Daniel", "idk", 2018)

  //import the package if you are not in the proper package
  val princess = new Princess //package automatically added
  //You can also use val princess = new Lecture.Lecture_basics.CinderellaForTest

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharmingForTest

  //import order

  // 1. use Fully Qualified Names
  import java.util.Date
  import java.sql.{Date => SqlDate}

  val d = new java.util.Date(2018,5,4)

  // 2. use aliasing
  val sqlDate = new SqlDate(2018,5,4)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, FUnction
  // scala, predef - println, ???








}
