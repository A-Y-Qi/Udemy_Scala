package Lecture.part2oop

import javax.swing.plaf.multi.MultiListUI

abstract class MyList {

  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is this list empty
  add(int) => new list with this element added
  toString => a string representation of the list
  */
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element:Int):MyList
  override def toString: String = "[" + printElements + "]"
  def printElements: String
  // we cannot use: protected def printElements: String here
  // because we are going to call an recursive function on another element t
  // which protected forbidden to call outside of the class

}

//Empty is made as an object because all empty lists are equal, there's not point to make empty a class
object Empty extends MyList {
  def head: Int = throw new NoSuchElementException

  def tail: MyList = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add(element: Int): MyList = new Cons(element, Empty)

  def printElements: String=""
}

class Cons (h:Int, t:MyList) extends MyList{
  def head: Int = h

  def tail: MyList = t

  def isEmpty: Boolean = false

  def add(element: Int): MyList = new Cons(element, this)

  def printElements: String = {
    if(t.isEmpty) s"$h"
    else s"$h ${t.printElements}"
  }
}

object ListTest extends App{
  val list = new Cons (1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).tail.head)
  println(list.isEmpty)

  //polymorphic call
  val list2 = list.add(4)
  println(list.toString)
  println(list2.toString)



}
