package org.commitlog

import java.util.UUID
import java.util.UUID.randomUUID
import java.time.LocalDate.now
import java.time.LocalDate

sealed abstract class State
case class Todo(value: String) extends State
case class Wip(value: String)  extends State
case class Done(value: String) extends State
case class Null(value: String) extends State

def transition(s: State) = s match {
  case Todo(value) => Wip(value)
  case Wip(value)  => Done(value)
  case Done(value) => Done(value)
  case Null(value) => Null(value)
}

def uuid() = randomUUID()

case class Entry(date: LocalDate, id: UUID, state: State)

object Entry {
  def from(state: State): Entry = {
    Entry(now(), uuid(), state)
  }
  def change(entry: Entry): Entry =
    Entry(entry.date, entry.id, transition(entry.state))
}

@main
def main(): Unit = {
  val initial = Todo("first case")
  val entry   = Entry.change(Entry.from(Wip("this is a case")))
  val entries = List(
    Entry.from(Todo("example")),
    Entry.from(Wip("example")),
    Entry.from(Done("example")),
    Entry.from(Wip("example")),
    Entry.from(Null("example"))
  )
  println(entries.map(x => Entry.change(x)))

  println(transition(initial))
  println(entry)
  println("Hello world!")
}
