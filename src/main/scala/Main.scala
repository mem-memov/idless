import mem.memov.idless.History

@main def main: Unit =
  val history = History()
  val singleLink = history.connectRootToRoot
  val twoTargets = singleLink.connectFirstToRoot(0)





