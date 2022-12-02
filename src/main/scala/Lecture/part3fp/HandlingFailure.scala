package Lecture.part3fp

import scala.util.{Failure, Success, Try}

object HandlingFailure extends App{
  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure (new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFail = Try {
    // code that may throw
    10/0
  }

  println(anotherPotentialFail)

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod():String="A Valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)


}
