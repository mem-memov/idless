package mem.memov.idless

class Cluster private (nodeBlock: => Vector[Vector[Cluster]]):

  lazy val nodes: Vector[Vector[Cluster]] = nodeBlock

  def withCluster(thisIndex: Int, thatIndex: Int, that: Cluster): Cluster =

    lazy val thisNode = this.nodes(thisIndex) :+ modifiedThat
    lazy val thatNode = that.nodes(thatIndex) :+ modifiedThis

    lazy val (thisPredecessors, (_ +: thisSuccessors)) = this.nodes.splitAt(thisIndex)
    lazy val (thatPredecessors, (_ +: thatSuccessors)) = that.nodes.splitAt(thatIndex)

    lazy val thisNodes = thisPredecessors :+ thisNode :++ thisSuccessors
    lazy val thatNodes = thatPredecessors :+ thatNode :++ thatSuccessors

    lazy val modifiedThis: Cluster = new Cluster(thisNodes)
    lazy val modifiedThat: Cluster = new Cluster(thatNodes)

    modifiedThis

  def turn(path: Vector[(Int, Int)]): Cluster =

    path.foldLeft(this)(
      (cluster, step) => cluster.nodes(step._1)(step._2)
    )

object Cluster {
  def empty: Cluster =
    new Cluster(Vector(Vector.empty))
}
