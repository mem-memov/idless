package mem.memov.idless

import org.scalatest.funsuite.AnyFunSuite

class ClusterSuite  extends AnyFunSuite:

  test("It builds a plain graph with 2 nodes") {
    val a = Cluster.empty
    val b = Cluster.empty

    val c = a.withCluster(0, 0, b)

    assert(c.nodes(0)(0).nodes(0)(0) == c)
  }
