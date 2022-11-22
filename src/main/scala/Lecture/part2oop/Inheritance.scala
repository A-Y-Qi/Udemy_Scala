package Lecture.part2oop

object Inheritance extends App{

  //single class inheritance
  sealed class Animal{ //unable to extend on other files
    val creatureType = "wild"
    //final def eat = println("nomnom")
    // will prevent the override of the function eat
    def eat = println("nomnom")
    private def eeat = println("nomnom") //private: only accessible inside the class only
    eeat
    protected def eeeat = println("nomnom") //protected: accessible in subclassed but not outside of the subclass
  }

  class Cat extends Animal{
    def crunch = {
      eeeat //able to run here
      println("crunch crunch")
    }
  }
  //Cat:
  val cat = new Cat
  val animal = new Animal
  cat.eat
  // cat.eeat will not be accessible from cat
  // animal.eeat is also undefined
  // cat.eeear or animal.eeeat will also not be defined
  cat.crunch

  //constructors
  class Person(name: String, age: Int){
    def this(name:String)=this(name,0) //then it will be okay if: extends Person(name)

  }
  //class Adult(name: String, age: Int, idCard: String) extends Person
  //This will not run because JVM will call the constructor of Person with the same constructor
  // as the class signature first. But in this case the signature of Person and Adult are different
  // Thus we need to specify the constructor of the parent class
  class Adult(name: String, age: Int, idCard: String) extends Person(name,age)

//  class Dog (override val creatureType: String) extends Animal {
//    override def eat = println("crunch crunch")
//
//    //override val creatureType: String = "Domestic"
//  }

  class Dog(dogType: String) extends Animal {
    override val creatureType = dogType
    //super-val or functions from parent class
    override def eat = {
      super.eat
      println("Crunch Crunch")
    }

  }

  val dog = new Dog ("K9")

  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)

  val unknownAnimal: Animal = new Dog ("K9")
  unknownAnimal.eat

  //preventing overrides:
  //  1. use final on member
  //  -e.g. final def eat = println("nomnom") --Unable to override in class Dog
  //  2. use final on class : will prevent the whole class being extended -- Unable to class Dog extends Animal
  //  -e.g. final class Animal {}
  //  3. seal the class = ABLE to extend on THIS file, BUT UNABLE to extend on OTHER files
  //  -see the animal class










}
