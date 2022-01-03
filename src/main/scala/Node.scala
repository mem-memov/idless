class Node private (
            sourceBlock: => List[Node],
            targetBlock: => List[Node],
            private[Node] val sourceCount: Int,
            private[Node] val targetCount: Int
          ):
  private[Node] lazy val sources: List[Node] = sourceBlock
  private[Node] lazy val targets: List[Node] = targetBlock

  def toNewSource: Node =
    lazy val modifiedTarget: Node = new Node(
      newSource :: sources,
      targets,
      sourceCount + 1,
      targetCount
    )
    lazy val newSource: Node = new Node(
      Nil,
      modifiedTarget :: Nil,
      0,
      1
    )
    newSource

  def toNewTarget: Node =
    lazy val modifiedSource: Node = new Node(
      sources,
      newTarget :: targets,
      sourceCount,
      targetCount + 1
    )
    lazy val newTarget: Node = new Node(
      modifiedSource :: Nil,
      Nil,
      1,
      0
    )
    newTarget

  def toExistingSource(existing: Node): Node =
    lazy val modifiedTarget: Node = new Node(
      newSource :: sources,
      targets,
      sourceCount + 1,
      targetCount
    )
    lazy val newSource: Node = new Node(
      existing.sources,
      modifiedTarget :: existing.targets,
      existing.sourceCount,
      existing.targetCount + 1
    )
    newSource

  def toExistingTarget(existing: Node): Node =
    lazy val modifiedSource: Node = new Node(
      sources,
      newTarget :: targets,
      sourceCount,
      targetCount + 1
    )
    lazy val newTarget: Node = new Node(
      modifiedSource :: existing.sources,
      existing.targets,
      existing.sourceCount + 1,
      existing.targetCount
    )
    newTarget

  def toSource(count: Int): Option[Node] =
    if count <= 0 || count > sourceCount then
      None
    else
      Some(sources.drop(sourceCount - count).head)

  def toTarget(count: Int): Option[Node] =
    if count <= 0 || count > targetCount then
      None
    else
      Some(targets.drop(targetCount - count).head)

object Node:
  def apply() =
    new Node(Nil, Nil, 0, 0)