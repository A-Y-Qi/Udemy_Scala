package Lecture.part3fp

object TuplesAndMaps extends App{
  /*
  Tuples = Finite ordered "Lists"
  */
  val aTuple = new Tuple2[Int,String](2, "hello scala") // or (2, "hello, scala")
  println(aTuple._1) //print the first element in tuple (1 indexed)
  println(aTuple.copy(_2="goodbye Java"))
  println(aTuple.swap)  // ("hello, Scala", 2)

  /*
  Maps:
    - keys->values
  */
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel"-> 666).withDefaultValue(-1)
  //a -> b is a sugar for (a,b)
  println(phonebook)

  //map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Mary")) // will throw exceptions if there is no default value assigned

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook+newPairing
  println(newPhonebook)


  // functionals on maps
  // map, flatMap, filter

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.view.filterKeys(x=>x.startsWith("J")).toMap)
  //mapValues
  println(phonebook.view.mapValues(number=>number * 10).toMap)
  println(phonebook.view.mapValues(number=>"0245-"+number).toMap)

  //conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel",555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
  See map exercises in the exercise folder
  */







}
