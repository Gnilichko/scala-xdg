package org.freedesktop
package build

import sbt._
import Keys._

object XDGProject {
  def apply(name: String, path: String): Project = (
    Project(name, file(path))
    settings(commonSettings: _*)
  )
}
