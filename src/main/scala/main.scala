package org.commitlog

sealed abstract class State
case class Todo(value: String) extends State
case class Wip(value: String) extends State
case class Done(value: String)  extends State
case class Null(value: String) extends State

def transition (s: State) = s match {
  case Todo(value) => Wip(value)
  case Wip(value) => Done(value)
  case Done(value) => Done(value)
  case Null(value) => Null(value)
}
@main
def main(): Unit = {
  val initial = Todo("first case")
  println(transition(initial))
  println("Hello world!")
}
