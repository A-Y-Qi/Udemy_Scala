package ExerciseForPart3

import Lecture.part2oop.OOPExercise.{Cons, Empty, MyList3}

object HOFsCurriesExercise extends App{
  /*
  1. Expand MyList
    - foreach method A => Unit
      [1,2,3].foreach(x=>println(x))
    - sort function ((A,A) => Int) => MyList
      [1,2,3].sort((x,y)=>y-x)=>[3,2,1]
    - zipWith (list, (A,A)=>B) => MyList[B]
      [1,2,3].zipWith([4,5,6], x*y) => [1*4, 2*5, 3*6] = [4,10,18]
    - fold(start)(function) => a value
      [1,2,3].fold(0)(x+y) = 6
  */

  abstract class MyList3[+A] {
    def head: A

    def tail: MyList3[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList3[B]

    override def toString: String = "[" + printElements + "]"

    def printElements: String

    def map[B](transformer: MyTransformer[A, B]): MyList3[B]

    def flatMap[B](transformer: MyTransformer[A, MyList3[B]]): MyList3[B]

    def filter(predicate: MyPredicate[A]): MyList3[A]

    //concatenation
    def ++[B >: A](list: MyList3[B]): MyList3[B]

    //HOFs
    def foreach(f: A=>Unit): Unit

    def sort(compare: (A,A) => Int): MyList3[A]

    def zipwith[B,C](list: MyList3[B], f:(A,B)=>C): MyList3[C]

    def fold[B](start:B)(operator: (B,A)=>B):B

  }

  case object Empty extends MyList3[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList3[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B >: Nothing](element: B): MyList3[B] = new Cons(element, Empty)

    def printElements: String = ""

    def map[B](transformer: MyTransformer[Nothing, B]): MyList3[B] = Empty

    def flatMap[B](transformer: MyTransformer[Nothing, MyList3[B]]): MyList3[B] = Empty

    def filter(predicate: MyPredicate[Nothing]): MyList3[Nothing] = Empty

    def ++[B >: Nothing](list: MyList3[B]): MyList3[B] = list

    //HOFs
    def foreach(f:Nothing => Unit): Unit = ()  //This is the Unit value

    def sort(compare:(Nothing,Nothing) => Int)=Empty

    def zipwith[B, C](list: MyList3[B], f: (Nothing, B) => C): MyList3[C] = {
      if (!list.isEmpty) throw new RuntimeException("List does not have the same length")
      else Empty
    }

    def fold[B](start: B)(operator: (B, Nothing)=>B): B = start
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

    override def flatMap[B](transformer: MyTransformer[A, MyList3[B]]): MyList3[B] = {
      transformer.transform(this.h) ++ this.t.flatMap(transformer)
    }

    override def ++[B >: A](list: MyList3[B]): MyList3[B] = {
      new Cons(this.h, this.t ++ list)
    }

    //HOFs
    def foreach (f: A=>Unit): Unit={
      f(h)
      t.foreach(f)
    }

    def sort(compare: (A, A) => Int): MyList3[A] = {
      def insert(x: A, sortedList: MyList3[A]): MyList3[A] = {
        if (sortedList.isEmpty) new Cons(x, Empty)
        else if (compare(x, sortedList.head)<=0) new Cons(x,sortedList)
        else new Cons(sortedList.head, insert(x, sortedList.tail))
      }

      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }

    def zipwith[B, C](list: MyList3[B], f: (A, B) => C): MyList3[C] = {
      if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      else new Cons(f(h, list.head), t.zipwith(list.tail, f))
    }

    def fold[B](start: B)(operator: (B, A)=>B): B = {
      t.fold(operator(start, h))(operator)
    }

  }


  trait MyPredicate[-T] {
    def test(elem: T): Boolean

  }

  trait MyTransformer[-A, B] {
    def transform(elem: A): B
  }

  // tests
  val list2OfIntegers: MyList3[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val list2OfStrings: MyList3[String] = new Cons("Hello", new Cons("Scala", Empty))
  val anotherList2OfStrings: MyList3[String] = new Cons("Bye", new Cons("Java", Empty))
  val anotherList2OfIntegers: MyList3[Int] = new Cons(4, new Cons(5, Empty))

  list2OfIntegers.foreach(println)
  println(list2OfIntegers.sort((x,y)=>y-x))
  println(anotherList2OfStrings.zipwith(list2OfStrings, _+" "+_))
  println(anotherList2OfIntegers.zipwith[String, String](list2OfStrings, _+"-"+_))
  println(list2OfIntegers.fold(0)(_+_))

  /*
    2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
      fromCurry(f: (Int=>Int=>Int)) => (Int, Int) => Int
    */
  def toCurry(f:(Int,Int) => Int) = (x: Int)=>{(y: Int) => f(x,y)}
  def fromCurry(f:Int=>Int=>Int) = {
    (x: Int, y: Int)=>f(x)(y)
  }
  val add10 = toCurry((x,y)=>x+y)(10)
  println(s"the test result for toCurry is ${add10(10)}")
  val superAdd = (x: Int) => (y:Int) => x+y
  val curryTest = (x: Int)=> {(y: Int) => x-y}
  val minus10 = fromCurry(curryTest)(5,10)
  val simpleAdder = fromCurry(superAdd)(5,10)
  println(s"the test result for fromCurry is ${minus10}")
  println(s"the test result for fromCurry is ${simpleAdder}")




  /*
    3. compose(f,g) => x => f(g(x))
       andThen(f,g) => x => g(f(x))
  */

  def compose[A,B,T](f: A => B, g: T => A): T=>B = {
    x => f(g(x))
  }

  def andThen[A,B,T](f: A=>B, g: B => T): A => T ={
    x=>g(f(x))
  }

  val testCompose = compose[Int,Int,Int](x=>x*2, x=>x%3)
  println(testCompose(4))
  val testAndThen = andThen[Int, Int, Int](x=>x*x, x=> x-(x%10))
  println(testAndThen(9))

  // for comprehensions
  val combinations = for {
    n <- list2OfIntegers
    string <- list2OfStrings
  } yield n + "-" + string

  println(combinations)






}
