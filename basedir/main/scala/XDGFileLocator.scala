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


package org.freedesktop
package basedir

import java.io.File
import java.io.File._

import XDGBaseDirs._

/** $LocatorInfo */
object XDGFileLocator extends XDGFileLocator

/** $LocatorInfo
  *
  * @define LocatorInfo Locates files according to the
  * [[http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html XDG Base Directory
  * Specification]].
  *
  * @define AppInfo the name of the application / library for the sub-base directory
  * @define SubInfo the sub-path including the searched file
  */
trait XDGFileLocator {

  private[basedir] def paths(bases: Seq[String], app: String, sub: Seq[String]): Seq[String] = for {
    base ← bases
    appBase = base + separator + app
    path = appBase + sub.mkString(separator, separator, "")
  } yield path

  private[basedir] def existingPaths(bases: Seq[String], app: String, sub: Seq[String]): Seq[String] =
    paths(bases, app, sub) filter { path ⇒ new File(path).exists }

  private[basedir] def locate(bases: Seq[String], app: String, sub: Seq[String]): Option[String] =
    existingPaths(bases, app, sub).headOption

  /** Locates the precedented configuration file.
    *
    * @param app $AppInfo
    * @param sub $SubInfo
    */
  def locateConfig(app: String, sub: String*): Option[String] =
    locate(xdgConfigDirs, app, sub)

  /** Locates the precedented data file.
    *
    * @param app $AppInfo
    * @param sub $SubInfo
    */
  def locateData(app: String, sub: String*): Option[String] =
    locate(xdgDataDirs, app, sub)

}
