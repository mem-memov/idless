package mem.memov.idless

import scala.collection.mutable.ArrayBuffer

class Cluster (private[Cluster] val nodes: ArrayBuffer[ArrayBuffer[Cluster]]):

  def link(thisIndex: Int, thatIndex: Int, that: Cluster): Unit =

    if thisIndex >= this.nodes.length then
      this.nodes.appendAll(
        ArrayBuffer.fill(1 + thisIndex - this.nodes.length)(ArrayBuffer.empty)
      )

    if thatIndex >= that.nodes.length then
      that.nodes.appendAll(
        ArrayBuffer.fill(1 + thatIndex - that.nodes.length)(ArrayBuffer.empty)
      )

    this.nodes(thisIndex).append(that)
    that.nodes(thatIndex).append(this)

  def beat(path: Vector[Step]): Cluster =
    path.foldLeft(this)(
      (cluster, step) =>

        if step.dimension >= cluster.nodes.length then
          this.nodes.appendAll(
            ArrayBuffer.fill(1 + step.dimension - cluster.nodes.length)(ArrayBuffer.empty)
          )

        if step.distance >= this.nodes(step.dimension).length then
          this.nodes(step.dimension).appendAll(
            ArrayBuffer.fill(1 + step.distance - this.nodes(step.dimension).length)(Cluster.empty)
          )

        this.nodes(step.dimension)(step.distance)
    )

object Cluster:
  def empty: Cluster =
    new Cluster(ArrayBuffer.empty)

