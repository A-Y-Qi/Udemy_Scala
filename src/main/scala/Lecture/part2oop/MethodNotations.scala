package Lecture.part2oop
import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, val favouriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favouriteMovie

    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name, what the heck?!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"

    /*
      * Exercise 1
        - Overload the + operator (Input is a string)
        - eg: mary + "the rock star" => new person "Mary (the rockstar)"
      */
    def +(nickName: String): Person = {
      new Person(s"${this.name} ($nickName)", this.favouriteMovie, 20)
    }

    /*
    *  Exercise 2
      - Add an age to the Person class
      - Add a unary + operator => new person with the age+1
      - +mary=>mary with the age increment
    */

    def unary_+ : Person = {
      new Person(this.name, this.favouriteMovie, this.age + 1)
    }

    /*
    * Exercise 3
      - add a learns method where input is a string
        -produce "mary learns scala"
      -Add a leanScala method, calls learns method with "Scala"
      - Use it in postfix notation
    */

    def learns(item: String): String = {
      s"${this.name} learns $item"
    }

    def learnsScala: String = {
      learns("Scala")
    }

    /*
    * Exercise 4
      - Overload apply method
      -mary.apply(2) => "Mary watched Inception 2 times"
    */
    def apply(times: Int):String = {
      s"${this.name} watched ${this.favouriteMovie} ${times} times"
    }





  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (works with method with only on parameter)
  // This is an example of syntactic sugar

  //"operators" in scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom) //+ is not reserved in scala thus we can use it as method name

  println(1 + 2)
  println(1.+(2)) //The plus with integers is also a method
  //All operators are methods

  // prefix notation
  val x = -1 //equivalent with 1.unary_-
  val y = 1.unary_-
  // Unary_prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  //postfix notation
  println(mary.isAlive)
  println(mary isAlive) //you need to import scala.language.postfixOps to run this now

  //apply
  println(mary.apply())
  println(mary()) //equivalent, object called as function = object.apply()

  /*
    * Exercise 1
  */

  println((mary + "star")())

  /*
    * Exercise 2
  */
  println((+mary).age)

  /*
  * Exercise 3
} */
  println(mary learns "java")
  println(mary learnsScala)        

  /*
  * Exercise 4
  */
  println(mary(2))


}

