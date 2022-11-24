package Lecture.part2oop

object OOPExercise extends App {
  abstract class MyList3[+A] {
    def head: A

    def tail: MyList3[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList3[B]

    override def toString: String = "[" + printElements + "]"

    def printElements: String

    def map[B](transformer: MyTransformer[A,B]): MyList3[B]

    def flapMap[B](transformer: MyTransformer[A,MyList3[B]]): MyList3[B]

    def filter(predicate: MyPredicate[A]): MyList3[A]

    //concatenation
    def ++[B >: A](list:MyList3[B]): MyList3[B]

  }

  case object Empty extends MyList3[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList3[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B >: Nothing](element: B): MyList3[B] = new Cons(element, Empty)

    def printElements: String = ""

    def map[B](transformer: MyTransformer[Nothing, B]): MyList3[B] = Empty

    def flapMap[B](transformer: MyTransformer[Nothing, MyList3[B]]): MyList3[B] = Empty

    def filter(predicate: MyPredicate[Nothing]): MyList3[Nothing] = Empty

    def ++[B >: Nothing](list:MyList3[B]): MyList3[B] = list
  }

  case class Cons[+A](h: A, t: MyList3[A]) extends MyList3[A] {
    def head: A = h

    def tail: MyList3[A] = t

    def isEmpty: Boolean = false

    def add[B >: A](element: B): MyList3[B] = new Cons(element, this)

    def printElements: String = {
      if (t.isEmpty) s"$h"
      else s"$h ${t.printElements}"
    }

    def filter(predicate: MyPredicate[A]): MyList3[A] = {
      if (predicate.test(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate) // will not produce anything include the head, but will return the tail predicate
    }

    def map[B](transformer: MyTransformer[A, B]): MyList3[B] = {
      new Cons(transformer.transform(h), t.map(transformer))
    }

    override def flapMap[B](transformer: MyTransformer[A, MyList3[B]]): MyList3[B] = {
      transformer.transform(this.h) ++  this.t.flapMap(transformer)}

    override def ++[B >: A](list: MyList3[B]): MyList3[B] = {
      new Cons(this.h , this.t ++ list)
    }
  }


  /*
  1. Generic trait MyPredicate[-T] with a little method test[T] => Boolean
  2. Generic trait MyTransformer[-A,B] with a method transform(A) => B
  3. MyLIst:
      - map (transformer) => MyList
      - filter (predicate) => MyList
      - flatMap (transformer from A to MyList[B]) => MyList[B]

      class EvenPredicate extends MyPredicate[Int]
      class StringToIntTransformer extends MyTransformer[String, Int]

      [1,2,3].map(n*2) => [2,4,6]
      [1,2,3,4].filter(n%2) => [2,4]
      [1,2,3].flatMap(n=>[n,n+1]) => [1,2,2,3,3,4]
*/


  trait MyPredicate[-T] {
    def test(elem: T): Boolean 

  }

  trait MyTransformer[-A, B] {
    def transform(elem: A): B
  }


  val list2OfIntegers: MyList3[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneList2OfIntegers: MyList3[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val list2OfStrings: MyList3[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(list2OfIntegers.toString)
  println(list2OfStrings.toString)

  println(list2OfIntegers.map(new MyTransformer[Int,Int] {
  override def transform(elem: Int): Int = elem*2
  }).toString)

  println(list2OfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = (elem%2==0)
  }).toString)

  println(list2OfIntegers.flapMap(new MyTransformer[Int, MyList3[Int]] {
    override def transform (elem: Int): MyList3[Int] = new Cons[Int](elem, new Cons[Int](elem+1, Empty))
  }).toString)

  println(list2OfIntegers == cloneList2OfIntegers)


}




