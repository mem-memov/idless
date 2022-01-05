package mem.memov.idless

class Node private (sourceBlock: => Vector[Node], targetBlock: => Vector[Node]):

  lazy val sources: Vector[Node] = sourceBlock
  lazy val targets: Vector[Node] = targetBlock

  def withSource(node: Node): Node =
    if sources.length <= node.targets.length then
      lazy val modifiedTarget: Node = new Node(sources :+ newSource, targets)
      lazy val newSource: Node = new Node(node.sources, node.targets :+ modifiedTarget)
      modifiedTarget
    else
      node.withTarget(this).targets.last

  def withTarget(node: Node): Node =
    if targets.length <= node.sources.length then
      lazy val modifiedSource: Node = new Node(sources, targets :+ newTarget)
      lazy val newTarget: Node = new Node(node.sources :+ modifiedSource, node.targets)
      modifiedSource
    else
      node.withSource(this).sources.last

object Node:
  def empty =
    new Node(Vector.empty, Vector.empty)