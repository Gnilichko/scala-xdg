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


package org.freedesktop.basedir

import java.io.File._
import util.Properties._

/** Provides static access to the XDG base directories.
  *
  * The original documentation of these directories and their default values is located at the
  * [[http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html XDG Base Directory
  * Specification]].
  */
object XDGBaseDirs extends XDGBaseDirs

/** Provides access to the XDG base directories for mixin composition.
  *
  * The original documentation of these directories and their default values is located at the
  * [[http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html XDG Base Directory
  * Specification]].
  */
trait XDGBaseDirs extends XDGBaseDirEnvironment {

  // -----------------------------------------------------------------------------------------------
  // user-specific base directories
  // -----------------------------------------------------------------------------------------------

  /** Returns the absolute path to the base directory for user-specific non-essential (cached) data.
    *
    * Should the related environment variable not be set, `userHome + separator + ".cache"` is
    * chosen as the default.
    */
  def xdgCacheHome = envOrElse(XDG_CACHE_HOME, userHome + separator + ".cache")

  /** Returns the absolute path of the base directory for user-specific configuration files.
    *
    * Should the related environment variable not be set, `userHome + separator + ".config"` is
    * chosen as the default.
    */
  def xdgConfigHome = envOrElse(XDG_CONFIG_HOME, userHome + separator + ".config")

  /** Returns the absolute path of the base directory for user-specific data files.
    *
    * Should the related environment variable not be set, `userHome + separator + ".local" +
    * separator + "share"` is chosen as the default.
    */
  def xdgDataHome = envOrElse(XDG_DATA_HOME, userHome + separator + ".local" + separator + "share")

  /** Returns the base directory for user-specific runtime files and other file objects.
    *
    * Should the related environment variable not be set, `tmpDir + separator + userName` is chosen
    * as the default.
    */
  def xdgRuntimeDir = envOrElse(XDG_RUNTIME_DIR, tmpDir + separator + userName)

  // -----------------------------------------------------------------------------------------------
  // preference ordered (global) base directories
  // -----------------------------------------------------------------------------------------------

  /** Returns the distinct global preference ordered base directories relative to which
    * configuration files are searched.
    *
    * Should the related environment variable not be set, `List("/etc/xdg", "/etc")` is chosen as
    * the default.
    */
  def xdgGlobalConfigDirs: List[String] = envOrNone(XDG_CONFIG_DIRS) map {
    _.split(pathSeparator).toList.distinct
  } getOrElse {
    List("/etc/xdg", "/etc")
  }

  /** Returns the distinct global preference ordered base directories relative to which data files
    * are searched.
    *
    * Should the related environment variable not be set, `List("/usr/local/share", "/usr/share")`
    * is chosen as the default.
    */
  def xdgGlobalDataDirs: List[String] = envOrNone(XDG_DATA_DIRS) map {
    _.split(pathSeparator).toList.distinct
  } getOrElse {
    List("/usr/local/share", "/usr/share")
  }

  // -----------------------------------------------------------------------------------------------
  // preference ordered base directories (including user-specific)
  // -----------------------------------------------------------------------------------------------

  /** Returns the distinct preference ordered base directories relative to which configuration files
    * are searched.
    */
  def xdgConfigDirs: List[String] = (xdgConfigHome :: xdgGlobalConfigDirs).distinct

  /** Returns the distinct preference ordered base directories relative to which data files are
    * searched.
    */
  def xdgDataDirs: List[String] = (xdgDataHome :: xdgGlobalDataDirs).distinct

}
