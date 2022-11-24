package Lecture.part2oop

object Enums {

  /*
  * Enums
  - A datatype much like a class
  */

  enum Permissions{
    // A datatype that only have a bunch of instances that we create in the class/body
    // use case to create instances
    case READ, WRITE, EXECUTE, NONE

    //You can also add fields or methods
    def openDocument(): Unit =
      if (this == READ) println("Opening Document...")
      else println ("Reading not allowed")
  }

  val somePermissions: Permissions = Permissions.READ //created an instance as Permissions.READ


  // Constructor Arguments
  enum PermissionWithBits(bits:Int){
    case READ extends PermissionWithBits(4) 
    case WRITE extends PermissionWithBits(2)
    case EXECUTE extends PermissionWithBits(1)
    case NONE extends PermissionWithBits(0)
  }

  object PermissionWithBits{
    def fromBits(bits: Int): PermissionWithBits = PermissionWithBits.NONE
  }

  //standard API
  val somePermissionOrdinal = somePermissions.ordinal
  val allPermissions=PermissionWithBits.values //array of all possible values of enum
  val readPermission: Permissions = Permissions.valueOf("READ") //Permission.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionOrdinal)
    println(allPermissions)
    readPermission.openDocument()
  }



}
