package Lecture.part2oop

object Generics extends App {
  class MyList[+A] //Covariant
  {
    // A generic type A
    def add[B >: A](element: B): MyList[B] = ???
  }

  class MyMap[key, value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  //generic methods
  object MyList {
    def empty[A]: MyList[A] = MyList[A]
  }

  val emptyListOfIntegers = MyList.empty[Int]

  //Variance Problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  //Q: If Cat extends Animal, Does list[Cat] extends list[Animal]?

  //Case 1: Yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //Can we do animalList.add(new Dog)??? Hard Question
  //A: we return a list of animals

  //Case 2: No, List[Cat] and List[Animal] are two different things = INVARIANCE
  class InvariantList[A]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //Case 3: Hell, no! CONTRAVARIANCE
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]



  //bounded types
  /*class Cage[A<: Animal] (animal: A)// subtype of Animal A
  // class Cage[A>: Animal] (animal: A)// supertype of Animal A
  val cage = new Cage(new Dog)*/

  /*
  class Car
  val newCage = new Cage(new Car) **will produce error
  */


  /*
  Exercise:
  Expand MyList2 class to be generic
  */
  abstract class MyList2[+A] {
    def head: A

    def tail: MyList2[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList2[B]

    override def toString: String = "[" + printElements + "]"

    def printElements: String


  }

  object Empty extends MyList2[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList2[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B >: Nothing](element: B): MyList2[B] = new Cons(element, Empty)

    def printElements: String = ""
  }

  class Cons[+A](h: A, t: MyList2[A]) extends MyList2[A] {
    def head: A = h

    def tail: MyList2[A] = t

    def isEmpty: Boolean = false

    def add[B >: A](element: B): MyList2[B] = new Cons(element, this)

    def printElements: String = {
      if (t.isEmpty) s"$h"
      else s"$h ${t.printElements}"
    }
  }

  val list2OfIntegers: MyList2[Int]=new Cons (1, new Cons (2, new Cons (3, Empty)))
  val list2OfStrings: MyList2[String]=new Cons ("Hello", new Cons ("Scala", Empty))

  println(list2OfIntegers.toString)
  println(list2OfStrings.toString)












}













