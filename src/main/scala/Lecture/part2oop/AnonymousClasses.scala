package Lecture.part2oop

object AnonymousClasses extends App{

  abstract class Animal{
    def eat:Unit
  }

  val funnyAnimal: Animal = new Animal{
    override def eat: Unit = println("hahahahahahahah")
  }

  // is equivalent to
  class AnonymousClass$$anon$1 extends Animal{
    override def eat: Unit = println("hahahahahahahah")
  }
  val funnyAnimal_2: Animal = new AnonymousClass$$anon$1


  println(funnyAnimal.getClass)
  println(funnyAnimal_2.getClass)

  /*
  * Rules:
    1. pass in required constructor arguments if needed
    2. implement all abstract fields/methods
  * It works for both class and traits
  */

  class Person(name:String){
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help")
  }

  val jim = new Person("Jim"){
    override def sayHi: Unit = println(s"Hi, this is Jim, how can I be of service")
  }


}
