package org.functionalkoans.forscala.support

import org.scalatest.events.Event
import org.scalatest.Stopper
import org.scalatest.events.{TestPending, TestFailed, TestIgnored, Event}

object Master extends Stopper {
  var studentNeedsToMeditate = false

  override def apply() = studentNeedsToMeditate

  type HasTestNameAndSuiteName = {
    val suiteName: String
    val testName: String
  }


  def studentFailed (event: HasTestNameAndSuiteName): String = {
    studentNeedsToMeditate = true
    event match {
      case e: TestFailed => meditationMessage(event) + event
      case e  => meditationMessage(e)
    }
  }

  private def meditationMessage(event: HasTestNameAndSuiteName) = {
    "Please meditate on koan \"%s\" of suite \"%s\"" format (event.testName, event.suiteName)
  }

}

