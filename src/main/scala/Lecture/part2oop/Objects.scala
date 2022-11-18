package Lecture.part2oop

object Objects {

  //CLASS DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person{ //type + its only instance
    //"static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    //factory method
    def apply(mother:Person,father:Person):Person = new Person("Bobby")

  }

  class Person(val name:String){
    //instance level functionality
  }
  //These two class and object are companions

  def main(args: Array[String]): Unit = {

    println(Person.N_EYES)
    println(Person.canFly)

    //Scala object = SINGLETON INSTANCE
    // when I create an object, I also created a single instance
    val mary = Person
    val john = Person
    println(mary == john) //true because they are pointing to the same instance (object)

    //But if we are using class
    val tom = new Person("Tom")
    val jerry = new Person("Mary")
    println(tom == jerry) //false because they are created with a new class, not the same instance

    val bobby = Person(tom, jerry)
  }

    //Scala Applications = Scala object with
    // def main(args: Array[String]): Unit
    // or we can use extends App



}
