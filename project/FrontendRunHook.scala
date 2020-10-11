import play.sbt.PlayRunHook
import sbt._

import scala.sys.process.Process

object FrontendRunHook {
  def apply(base: File): PlayRunHook = {
    object UIBuildHook extends PlayRunHook {

      var process: Option[Process] = None

      val install: String = FrontendCommands.dependencyInstall
      val buildDev: String = FrontendCommands.buildDev

      val frontendDir = "frontend"

      override def beforeStarted(): Unit = {
        if (!(base / frontendDir / "node_modules").exists) Process(install, base / frontendDir).!
      }

      override def afterStarted(): Unit = {
        process = Some(Process(buildDev, base / frontendDir).run)
      }

      override def afterStopped(): Unit = {
        process.foreach(p => p.destroy())
        process = None
      }
    }

    UIBuildHook
  }
}
