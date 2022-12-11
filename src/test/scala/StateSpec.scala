package org.commitlog

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.ParallelTestExecution

class StateSpec extends AnyFlatSpec with ParallelTestExecution {

  val message = "example message"
  val cases = List(
    // (input, expected)
    ( Wip(message), Done(message)),
    (Done(message), Done(message)),
    (Todo(message),  Wip(message)),
    (Null(message), Null(message)),
  )

  cases.foreach { case (input, expected) =>
    s"Transition from ($input)" should s"to \"$expected\"" in {
      assertResult(expected) { transition(input) }
    }

  }
}
