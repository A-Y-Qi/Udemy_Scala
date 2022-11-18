package Lecture.part2oop

object OOBasics extends App{

  val person= new Person("John", 26)
  // class parameters are NOT Fields
  // you need to add val or var to declare a parameter is a field
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()


  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectation", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter=new Counter
  counter.inc(5).print




}

class Person(val name: String, val age:Int = 0) //constructor
{
  //body
  val x=2 //person.x any val or var declared in side the block is a field

  println(1+3)


  //Method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet():Unit=println(s"Hi, I am $name") //Since this method takes different parameters, so the system is able to recognize it

  //multiple constructors
  def this(name: String) = this(name, 0 ) //This can be solved by adding default values in the primary constructor
}

/*
  Exercise 1:
  Novel and Writer
  Writer: First name, Last name, year of birth
  - Method: fullName (return the concatenation of firstname and last name)

  Novel: Name, year of release, author
  - authorAge: Age of the author at year of release
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel with new year of release
*/

class Writer(val firstName: String, val lastName: String, val yearOfBirth: Int){
  def fullName: String = firstName+" "+lastName
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer){
  def authorAge:Int = yearOfRelease-author.yearOfBirth

  def isWrittenBy(author:Writer): Boolean= author == this.author

  def copy(newYear:Int): Novel = {
    new Novel(this.name, newYear, this.author)
  }
}

/*
  Exercise 2:
  Counter class
  -receives an int value
  -method to increment/decrement => new Counter (+-1)
  -overload inc/dec to receive an amount =>new Counter increase/decrease by the amount
*/

class Counter(val count: Int = 0){
  def inc: Counter= {
    println("Incrementing")
    new Counter(count+1)
  }
  def dec: Counter={
    println("Decrementing")
    new Counter(count-1)
  }

  def inc(n:Int): Counter ={
    if (n<=0) this
    else inc.inc(n - 1) //The first inc is the previous inc, the second one is this one with one parameter
  }
  def dec(n:Int): Counter ={
    if (n <= 0) this
    else dec.dec(n - 1) //The first dec is the previous dec, the second one is this one with one parameter

  }

  def print:Unit = println(count)

}

