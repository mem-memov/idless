package mem.memov.idless

import org.scalatest.funsuite.AnyFunSuite

class ClusterSuite  extends AnyFunSuite:

  test("It builds a plain graph with 2 vertices") {
    val a = Cluster.empty
    val b = Cluster.empty

    a.link(0, 0, b)

    assert(a.beat(Vector(Step(0, 0))) == b)
    assert(b.beat(Vector(Step(0, 0))) == a)
  }

  test("It builds a directed graph with 2 vertices and 1 arrow") {
    val a = Cluster.empty
    val b = Cluster.empty

    a.link(0, 1, b)

    assert(a.beat(Vector(Step(0, 0))) == b)
    assert(b.beat(Vector(Step(1, 0))) == a)
  }

  test("It builds a directed graph with 2 vertices and 2 arrow") {
    val a = Cluster.empty
    val b = Cluster.empty

    a.link(0, 1, b)
    b.link(1, 0, a)

    assert(a.beat(Vector(Step(0, 1))) == b)
    assert(b.beat(Vector(Step(1, 0))) == a)
  }
