package mem.memov.idless

class Node private (sourceBlock: => Vector[Node], targetBlock: => Vector[Node]):

  lazy val sources: Vector[Node] = sourceBlock
  lazy val targets: Vector[Node] = targetBlock

  def withTarget(): Node =
    lazy val modifiedSource: Node = new Node(sources, targets :+ newTarget)
    lazy val newTarget: Node = new Node(Vector(modifiedSource), Vector.empty)
    modifiedSource

  def withSource(): Node =
    lazy val modifiedTarget: Node = new Node(sources :+ newSource, targets)
    lazy val newSource: Node = new Node(Vector.empty, Vector(modifiedTarget))
    modifiedTarget

  def withTarget(node: Node): Node =
    if targets.length <= node.sources.length then
      lazy val modifiedSource: Node = new Node(sources, targets :+ newTarget)
      lazy val newTarget: Node = new Node(node.sources :+ modifiedSource, node.targets)
      modifiedSource
    else
      node.withSource(this).sources.last

  def withSource(node: Node): Node =
    if sources.length <= node.targets.length then
      lazy val modifiedTarget: Node = new Node(sources :+ newSource, targets)
      lazy val newSource: Node = new Node(node.sources, node.targets :+ modifiedTarget)
      modifiedTarget
    else
      node.withTarget(this).targets.last

  def withTarget(path: Vector[(Boolean, Int)]): Node =
    val node = this.turn(path)
    this.withTarget(node)

  def withSource(path: Vector[(Boolean, Int)]): Node =
    val node = this.turn(path)
    this.withSource(node)

  def turn(path: Vector[(Boolean, Int)]): Node =
    path.foldLeft(this)(
      (node, step) => step match
        case (true, index) => node.targets(index)
        case (false, index) => node.sources(index)
    )

object Node:
  def empty =
    new Node(Vector.empty, Vector.empty)