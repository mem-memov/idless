package mem.memov.idless

class History private (records: Vector[(Node, Node)], root: Node):

  def connectRootToRoot: History =
    new History(
      records.appended(root.addTarget(root)),
      root
    )

  def connectFirstToRoot(sourceIndex: Int): History =
    val source = records(sourceIndex)._1
    new History(
      records.appended(source.addTarget(root)),
      root
    )

  def connectRootToFirst(targetIndex: Int): History =
    val target = records(targetIndex)._1
    new History(
      records.appended(root.addTarget(target)),
      root
    )

  def connectSecondToRoot(sourceIndex: Int): History =
    val source = records(sourceIndex)._2
    new History(
      records.appended(source.addTarget(root)),
      root
    )

  def connectRootToSecond(targetIndex: Int): History =
    val target = records(targetIndex)._2
    new History(
      records.appended(root.addTarget(target)),
      root
    )

  def connectFirstToFirst(sourceIndex: Int, targetIndex: Int): History =
    val source = records(sourceIndex)._1
    val target = records(targetIndex)._1
    new History(
      records.appended(source.addTarget(target)),
      root
    )

  def connectFirstToSecond(sourceIndex: Int, targetIndex: Int): History =
    val source = records(sourceIndex)._1
    val target = records(targetIndex)._2
    new History(
      records.appended(source.addTarget(target)),
      root
    )

  def connectSecondToSecond(sourceIndex: Int, targetIndex: Int): History =
    val source = records(sourceIndex)._2
    val target = records(targetIndex)._2
    new History(
      records.appended(source.addTarget(target)),
      root
    )

  def connectSecondToFirst(sourceIndex: Int, targetIndex: Int): History =
    val source = records(sourceIndex)._2
    val target = records(targetIndex)._1
    new History(
      records.appended(source.addTarget(target)),
      root
    )

object History:
  def apply(): History =
    new History(Vector.empty, Node())