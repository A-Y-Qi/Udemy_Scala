package Lecture.part2oop

object CaseClasses extends App{
  /*
  * For lightweight data structure like Class Person we defined before
  we need to redefine/reimplement many standard methods
  such as:
  equals, hashCode, toString
  * Case class allows to define a class and the companion object/sensible defaults in one go
  */

  case class Person(name: String, age: Int)
  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name) //directly turned field to parameters, otherwise we need to add val before parameter name

  // 2. sensible toString
  println(jim.toString)
  //with no case we will get: Lecture.part2oop.CaseClasses$Person@6bdf28bb
  //println(instance) = println(instance.toString)
  println(jim)

  // 3. equals and hashCode implemented Out Of The Box
  val jim2 = new Person ("Jim", 34)
  println(jim == jim2) //True

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  /*
  * Serialization is the conversion of the state of an object into a byte stream;
  * deserialization does the opposite. Stated differently,
  * serialization is the conversion of a Java object into a static stream (sequence) of bytes,
  * which we can then save to a database or transfer over a network.
  */

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom{
    def name: String = "The UK of GB and NI"
  }

  /*
  * Exercise:
  Expand MyList - use case classes and case objects

  see OOPExercise
  */







}
