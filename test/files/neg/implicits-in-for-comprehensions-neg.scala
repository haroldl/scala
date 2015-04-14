object ImplicitsInForComprehensionsNeg {

  def f(implicit i: Int) = i * 3
  def g(implicit s: String) = s + "!"

  // Should fail to compile because str is not marked implicit
  // so there is no implicit String available for g
  def neg1() = {
    for {
      implicit x <- List(1, 2, 3)
      str = "hello" * x
    } yield g
  }

  // Should fail to compile because y: String is implicit, not
  // x: Int. This test makes sure that the value definition does
  // not get folded into a tuple "implicit (x, y) <- ..." which
  // would mark both x and y implicit.
  def neg2() = {
    for {
      x <- List(1, 2, 3)
      implicit y = "number " + x
    } yield f
  }
}
