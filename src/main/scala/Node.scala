import scala.collection.Iterator

class Node private (
            sourceBlock: => List[Node],
            targetBlock: => List[Node],
            private[Node] val sourceCount: Int,
            private[Node] val targetCount: Int
          ):
  private[Node] lazy val sources: List[Node] = sourceBlock
  private[Node] lazy val targets: List[Node] = targetBlock

  def countSources: Int =
    sourceCount

  def countTargets: Int =
    targetCount

  def sourceIterator(): Iterator[Node]  =
    sources.iterator

  def targetIterator(): Iterator[Node]  =
    targets.iterator

  def hasSource(node: Node): Boolean =
    if sourceCount <= node.targetCount then
      sources.contains(node)
    else
      node.hasTarget(this)

  def hasTarget(node: Node): Boolean =
    if targetCount <= node.sourceCount then
      targets.contains(node)
    else
      node.hasSource(this)

  def addSource(node: Node): (Node, Node) =
    if hasSource(node) then
      (this, node)
    else
      if sourceCount <= node.targetCount then
        lazy val modifiedTarget: Node = new Node(
          newSource :: sources,
          targets,
          sourceCount + 1,
          targetCount
        )
        lazy val newSource: Node = new Node(
          node.sources,
          modifiedTarget :: node.targets,
          node.sourceCount,
          node.targetCount + 1
        )
        (modifiedTarget, newSource)
      else
        node.addTarget(this).swap

  def addTarget(node: Node): (Node, Node) =
    if hasTarget(node) then
      (this, node)
    else
      if targetCount <= node.sourceCount then
        lazy val modifiedSource: Node = new Node(
          sources,
          newTarget :: targets,
          sourceCount,
          targetCount + 1
        )
        lazy val newTarget: Node = new Node(
          modifiedSource :: node.sources,
          node.targets,
          node.sourceCount + 1,
          node.targetCount
        )
        (modifiedSource, newTarget)
      else
        node.addSource(this).swap

object Node:
  def apply() =
    new Node(Nil, Nil, 0, 0)