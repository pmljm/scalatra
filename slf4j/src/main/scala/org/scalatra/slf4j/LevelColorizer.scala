package org.scalatra
package slf4j

import ch.qos.logback.classic.pattern.ClassicConverter
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.Level
import util.RicherString._

object LevelColorizer {
  private val EndColor = "\u001b[m"
  private val ErrorColor = "\u001b[0;31m"
  private val WarnColor = "\u001b[0;33m"
  private val InfoColor = "\u001b[0;32m"
  private val DebugColor = "\u001b[0;37m"

  private val colors = Map(
    Level.TRACE -> DebugColor,
    Level.DEBUG -> DebugColor,
    Level.INFO -> InfoColor,
    Level.WARN -> WarnColor,
    Level.ERROR -> ErrorColor)

}
class LevelColorizer extends ClassicConverter {

  def convert(event: ILoggingEvent) = {
    import LevelColorizer._
    val c = colors.getOrElse(event.getLevel, "")
    "%s%s%s" format (c, event.getLevel, c.blankOption map (_ ⇒ EndColor) getOrElse "")
  }
}