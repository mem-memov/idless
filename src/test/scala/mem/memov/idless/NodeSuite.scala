package mem.memov.idless

import org.scalatest.funsuite.AnyFunSuite

class NodeSuite extends AnyFunSuite {

  test("An empty node has no targets") {
    val node = Node.empty

    assert(node.targets.isEmpty)
  }

  test("An empty node has no sources") {
    val node = Node.empty

    assert(node.sources.isEmpty)
  }

  test("A node has references to its target") {
    val node = Node.empty
    val target = Node.empty
    val modifiedNode = node.withTarget(target)

    assert(modifiedNode.targets.length == 1)
  }

  test("A node has references to its source") {
    val node = Node.empty
    val source = Node.empty
    val modifiedNode = node.withSource(source)

    assert(modifiedNode.sources.length == 1)
  }

  test("A target keeps a reference to node") {
    val node = Node.empty
    val target = Node.empty
    val modifiedNode = node.withTarget(target)

    assert(modifiedNode.targets(0).sources.length == 1)
    assert(modifiedNode.targets(0).sources(0) == modifiedNode)
  }

  test("A source keeps a reference to node") {
    val node = Node.empty
    val source = Node.empty
    val modifiedNode = node.withSource(source)

    assert(modifiedNode.sources(0).targets.length == 1)
    assert(modifiedNode.sources(0).targets(0) == modifiedNode)
  }

  test("Reflexive target connection splits the original node") {
    val node = Node.empty
    val modifiedNode = node.withTarget(node)

    assert(modifiedNode != modifiedNode.targets(0))
  }

  test("Reflexive source connection splits the original node") {
    val node = Node.empty
    val modifiedNode = node.withSource(node)

    assert(modifiedNode != modifiedNode.sources(0))
  }
}
