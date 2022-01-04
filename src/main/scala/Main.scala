@main def main: Unit =
  val root = Node()
  val source = Node()
  val target = Node()
  val (rootWithTarget, targetWithRoot) = root.addTarget(target)
  val (sourceWithRoot, rootWithSource) = source.addTarget(rootWithTarget)





