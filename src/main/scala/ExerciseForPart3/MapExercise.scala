package ExerciseForPart3

import scala.annotation.tailrec

object MapExercise extends App{
  /*
  1. What would happen if I had two original entries "Jim" -> 555 and "JIM"->900?
  */
  val phonebook= Map("JIM"->900, "Jim" -> 555,  "Bob"->345)
  println(phonebook.map(pair=>pair._1.toLowerCase->pair._2))
  //it will delete the first value and keep the newest key-value pair

  /*
  2. Overly simplified social network based on maps
    Person = String
    - add a person to the network
    - remove
    - friend (mutual)
    - unfriend

    - number of friends of a person
    - person with most friends
    - how many people have no friends
    - if there is a social connection between two people (direct or not)
  */

  def add(network: Map[String, Set[String]], personList: Set[String]): Map[String, Set[String]]=
    {
      if (personList.isEmpty) network
      else add(network+(personList.head -> Set()),personList.tail)
    }

  def friend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA + personB) ) + (personB -> (friendsB + personA) )
  }

  def unfriend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))

  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux (friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(empty, Set("Bob", "Mary"))
  println(network)
  val newNetwork=friend(network, "Bob", "Mary")
  println(friend(network, "Bob", "Mary"))
  println(unfriend(newNetwork, "Bob", "Mary"))
  println(remove(newNetwork, "Bob"))

  //Jim, Bob, Mary
  var network2 = add(empty, Set("Mary","Bob","Jim"))
  network2 = friend(network2, "Bob", "Mary")
  network2 = friend(network2, "Jim", "Bob")
  println(network2)

  def nFriends (network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(network2, "Bob"))

  def mostFriends (network: Map[String, Set[String]]): String =
    // network.maxBy(pair => pair._2.size) returns the k-v pair with max size
    network.maxBy(pair => pair._2.size)._1 // take out the key

  println(mostFriends(network2))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int=
    // unlike maxBy, filterKeys only take the keys in the map
    network.view.filterKeys(k => network(k).size == 0 ).size
    // Other Alternative Ways
    // 1. network.count(pair => pair._2.isEmpty)
    // 2. network.filter(pair=>pair._2.isEmpty).size

  println(nPeopleWithNoFriends(network))

  def socialConnection (network:Map[String, Set[String]], personA: String, personB: String): Boolean={
    @tailrec
    def bfs (target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else{
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail) // considered == visited set
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person) ) //++ appending two sets
      }
    }
    bfs(personA, Set(), network(personA)+personA)
  }

  println(socialConnection(network2,"Mary","Jim"))





















}
