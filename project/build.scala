/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  This file is part of the project 'scala-xdg'.                                                *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  This project is free software. It comes without any warranty, to the extent permitted by     *
 *  applicable law. You can redistribute it and/or modify it under the terms of the Do What The  *
 *  Fuck You Want To Public License, Version 2, as published by Sam Hocevar.                     *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  See http://sam.zoy.org/wtfpl/COPYING for more details.                                       *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


import sbt._
import Keys._

object ScalaXDG extends Build {

  // -----------------------------------------------------------------------------------------------
  // settings
  // -----------------------------------------------------------------------------------------------

  lazy val projectInfo = Seq (
    description          := "Access to freedesktop.org Standards for Scala",
    homepage             := Some(url("https://github.com/wookietreiber/scala-xdg")),
    organization         := "org.freedesktop",
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

  lazy val baseSettings = Defaults.defaultSettings ++ projectInfo ++ Seq (
    version              := "0.1.0-SNAPSHOT",
    scalaVersion         := "2.10.0-RC1",
    crossScalaVersions   := Seq (
      "2.8.0", "2.8.1", "2.8.2",
      "2.9.0", "2.9.0-1", "2.9.1", "2.9.1-1", "2.9.2",
      "2.10.0-RC1"
    ),
    initialCommands in (Compile, consoleQuick) <<= initialCommands in Compile,
    initialCommands in Compile in console += """
      import org.freedesktop._
    """
  )

  // -----------------------------------------------------------------------------------------------
  // projects
  // -----------------------------------------------------------------------------------------------

  lazy val root = Project (
    id        = "scala-xdg",
    base      = file("."),
    aggregate = Seq ( basedir ),
    settings  = baseSettings
  )

  lazy val basedir = Project (
    id        = "scala-xdg-basedir",
    base      = file("basedir"),
    settings  = baseSettings ++ Seq (
      initialCommands in Compile in console += """
        import org.freedesktop.basedir._
      """
    )
  )

}
