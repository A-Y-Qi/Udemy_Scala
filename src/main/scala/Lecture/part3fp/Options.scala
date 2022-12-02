package Lecture.part3fp

import javax.naming.NotContextException
import scala.util.Random

object Options extends App{
  /*
  Option: An Option is a wrapper for a value that might be present or not
  NullPointerException: errors such as Null++, Null.size etc
    - Options are present in many places, such as in map (when a key DNE), or other build-in functions
  */
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // unsafe APIs

  def unsafeMethod(): String = null

  val result= Option(unsafeMethod()) //Some or None
  println(result)

  //chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN UNSAFE API
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get)  //Unsafe - Do Not Use This

  //map, flatmap, filter
  println(myFirstOption.map(_*2))
  println(myFirstOption.filter(x => x>10))
  println(myFirstOption.flatMap(x => Option(x*10)))

  // for - comprehensions

  /*
    Exercise
  */

  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "167.34.34.54.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  // obtain a host/port might or might not be there, and given these called the apply method
  //  which might or might not be a connection
  //  if we have the connection--call the connect method

  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return connection.apply(h,p)
    return null
  */
  val connection = host.flatMap(h=>port.flatMap(p => Connection.apply(h,p)))
  /*
    if (c != null)
      return c.connect
    return null
  */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(Null) else print (Some(connectionStatus.get))
  println(connectionStatus)
  /*
    if (status != null)
      println(status)
  */
  connectionStatus.foreach(println)

  /*
  Method 2: chained calls
  */

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  /*
  Method 3: for -comprehensions
  */
  val forConnectionStatus = for {
    host <- config.get ("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)











}
