@main def main: Unit =
  val root = Node()
  val newSource = root.toNewSource
  println(newSource)

  for target <- newSource.toTarget(1)
    source <- target.toSource(1)
    yield
      println(target)
      println(source)




