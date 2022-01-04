package mem.memov.idless

import org.scalatest.funsuite.AnyFunSuite

class NodeSuite extends AnyFunSuite {

  test("An empty node has no targets") {
    val node = Node()

    assert(node.countTargets == 0)
    assert(node.getTargets == Nil)
  }

  test("An empty node has no sources") {
    val node = Node()

    assert(node.countSources == 0)
    assert(node.getSources == Nil)
  }

  test("A node has references to its target") {
    val node = Node()
    val target = Node()
    val (modifiedNode, modifiedTarget) = node.addTarget(target)

    assert(modifiedNode.countTargets == 1)
    assert(modifiedNode.getTargets == List(modifiedTarget))
    assert(modifiedNode.hasTarget(modifiedTarget))
  }

  test("A node has references to its source") {
    val node = Node()
    val source = Node()
    val (modifiedNode, modifiedSource) = node.addSource(source)

    assert(modifiedNode.countSources == 1)
    assert(modifiedNode.getSources == List(modifiedSource))
    assert(modifiedNode.hasSource(modifiedSource))
  }

  test("A target keeps a reference to node") {
    val node = Node()
    val target = Node()
    val (modifiedNode, modifiedTarget) = node.addTarget(target)

    assert(modifiedTarget.countSources == 1)
    assert(modifiedTarget.getSources == List(modifiedNode))
    assert(modifiedTarget.hasSource(modifiedNode))
  }

  test("A source keeps a reference to node") {
    val node = Node()
    val source = Node()
    val (modifiedNode, modifiedSource) = node.addSource(source)

    assert(modifiedSource.countTargets == 1)
    assert(modifiedSource.getTargets == List(modifiedNode))
    assert(modifiedSource.hasTarget(modifiedNode))
  }

  test("Reflexive target connection splits the original node") {
    val node = Node()
    val (source, target) = node.addTarget(node)

    assert(source != target)
    assert(source.hasTarget(target))
    assert(target.hasSource(source))
  }

  test("Reflexive source connection splits the original node") {
    val node = Node()
    val (target, source) = node.addSource(node)

    assert(target != source)
    assert(target.hasSource(source))
    assert(source.hasTarget(target))
  }
}
