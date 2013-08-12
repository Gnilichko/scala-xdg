package org.freedesktop

import sbt._
import Keys._

package object build {
  val commonSettings = Seq (
    organization := "org.freedesktop",
    scalaVersion := "2.10.2",
    crossScalaVersions := Seq("2.9.3", "2.10.2", "2.11.0-M4"),
    sourceDirectory <<= baseDirectory(identity),
    initialCommands in (Compile, consoleQuick) <<= initialCommands in Compile,
    initialCommands in Compile in console += """
      import org.freedesktop._
    """,
    description          := "Access to freedesktop.org Standards for Scala",
    homepage             := Some(url("https://github.com/wookietreiber/scala-xdg")),
    organizationName     := "org.freedesktop",
    organizationHomepage := Some(url("http://www.freedesktop.org/")),
    startYear            := Some(2012),
    licenses             := Seq("WTFPL" → url("http://sam.zoy.org/wtfpl/COPYING")),
    scmInfo              := Some(ScmInfo(
      url("https://github.com/wookietreiber/scala-xdg"),
      "scm:git:git://github.com/wookietreiber/scala-xdg.git",
      Some("scm:git:https://github.com/wookietreiber/scala-xdg.git")
    ))
  )
}
