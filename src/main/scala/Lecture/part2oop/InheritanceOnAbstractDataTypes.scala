package Lecture.part2oop

import Lecture.part2oop.Inheritance.Dog

object InheritanceOnAbstractDataTypes extends App{

  //abstract class: Classes which contain unimplemented or abstract fields/methods
  //  -purpose: Abstract classes are good for defining "general" things
  //    that by default don't have some methods implemented, and you leave those for the subclasses.
  //  - 注意：val 和 var 在 abstract class 和 实例化 class 里要一致！
  abstract class Animal{
    val creatureType: String
    def eat: Unit
    }

  class Dog extends Animal{
    val creatureType: String = "Canine"
    def eat: Unit={
      println("crunch crunch")
    }
  }

  //traits
  //  -Scala does not support multiple inheritance
  //  -but you can extend an abstract class with traits
  trait Carnivore{
    def eat(animal:Animal): Unit
  }
  trait ColdBlooded {
    val coldBlooded: Boolean
  }

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    val creatureType: String="croc"
    val coldBlooded: Boolean = true
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //traits vs abstract classes
  /*
  * Both abstract classes and traits can have abstract and non-abstract members
  * Difference:
  * 1. traits do not have constructor parameters (Only in Scala 2)
  * 2. Multiple traits can be inherited by the same class
  * 3. Traits are behaviour, but abstract class are type of thing

  */


}
