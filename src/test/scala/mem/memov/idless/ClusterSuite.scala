package mem.memov.idless

import org.scalatest.funsuite.AnyFunSuite

class ClusterSuite  extends AnyFunSuite:

  test("It builds a plain graph with 2 nodes") {
    val a = Cluster(1)
    val b = Cluster(1)

    val a1 = a.withCluster(0, 0, b)

    assert(a1.nodes(0)(0).nodes(0)(0) == a1)
  }

  test("It builds a directed graph with 2 nodes") {
    val a = Cluster(2)
    val b = Cluster(2)

    val a1 = a.withCluster(0, 1, b)

    assert(a1.nodes(0)(0).nodes(1)(0) == a1)
  }

  test("It builds a bidirected graph with 2 nodes") {
    val a = Cluster(2)
    val b = Cluster(2)

    val a1 = a.withCluster(0, 1, b)

    println(a1)
    println(a1.nodes)
    println(a1.turn(Vector((0, 0))))
    println(a1.turn(Vector((0, 0))).nodes)
    println()

    val a2 = a1.withCluster(1, 0, a1.turn(Vector((0, 0))))

    println(a2)
    println(a2.nodes)
    println(a2.nodes(0)(0).nodes)
    println(a2.nodes(1)(0).nodes)


//    assert(a2.nodes(0)(0).nodes(1)(0) == a2)
  }
