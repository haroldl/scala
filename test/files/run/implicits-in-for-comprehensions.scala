//############################################################################
// Test implicits in for comprehensions (SIP-24)
//############################################################################

object Suite1 {
  def e(x: Int)(implicit y: Int) = x + y
  def f(implicit z: String) = z + "!"
  def g(implicit y: Int) = y * y + 7
  def h(implicit x: Long) = x - 2

  def runTests() {
    println("Suite 1")
    println("-------------------------------------")
    for (implicit s <- List("hello", "world")) println(f)
    println("-------------------------------------")
    println("" + (for (implicit s <- List("hello", "world")) yield f))
    println("-------------------------------------")
    for (implicit (s, i) <- List(("hello", 4), ("world", 5))) println(f)
    println("-------------------------------------")
    for (implicit (s, i) <- List(("hello", 4), ("world", 5)); implicit l <- List(123L)) println(f)
    println("-------------------------------------")
    for (implicit (s, i) <- List(("hello", 4), ("world", 5)); implicit l <- List(123L)) println(g)
    println("-------------------------------------")
    for (implicit (s, i) <- List(("hello", 4), ("world", 5)); implicit l <- List(123L)) println(h)
    println("-------------------------------------")
    for (implicit s <- List("hello", "world") if s.startsWith("h")) println(f)
    println("-------------------------------------")
    for (implicit s <- List("hello", "world") ; y = 4) println(f)
    println("-------------------------------------")
    for (implicit s <- List("hello", "world") ; y = s + "...") println(f)
    println("-------------------------------------")
    for (implicit s <- List("hello", "world") ; y = s + "..." ; r <- List(1,2,3)) println(f)
    println("-------------------------------------")
    for (implicit s <- List("hello", "world") ; y = s + "..." ; r <- List(1,2,3) ; implicit q <- List(7,8)) println((f, g))
    println("-------------------------------------")
    println("" + (for (implicit a <- List(1,2,3); b = a * 3; if b > a; c = e(b)) yield (a, b, c)))
    println("-------------------------------------")
    for (implicit a <- List(1,2,3); b = a * 3; if b > a; c = e(b)) println((a, b, c))
    println("-------------------------------------")
    for (a <- List(1,2,3); implicit b = a * 3; if b > a; c = e(b)) println((a, b, c))
    println("-------------------------------------")
    println("" + (for (a <- List(1,2,3); implicit b = a * 3; if b > a; c = e(b)) yield (a, b, c)))
    println("-------------------------------------")
  }
}

object Test {
  def main(args: Array[String]) {
    Suite1.runTests()
  }
}

//############################################################################
