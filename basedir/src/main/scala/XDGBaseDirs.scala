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

import java.io.File._
import util.Properties._

/** $BaseDirInfo */
object XDGBaseDirs extends XDGBaseDirs

/** $BaseDirInfo
  *
  * @define BaseDirInfo Provides access to the base directories of the
  * [[http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html XDG Base Directory
  * Specification]].
  *
  * @define UnsetEnvVar Should the related environment variable be unset
  */
trait XDGBaseDirs extends XDGBaseDirEnvironment {

  // -----------------------------------------------------------------------------------------------
  // user-specific base directories
  // -----------------------------------------------------------------------------------------------

  /** Returns the base directory for user-specific non-essential (cached) data.
    *
    * $UnsetEnvVar, `userHome + separator + ".cache"` is chosen as the default.
    *
    * @see [[XDG_CACHE_HOME]]
    */
  def xdgCacheHome = envOrElse(XDG_CACHE_HOME, userHome + separator + ".cache")

  /** Returns the base directory for user-specific configuration files.
    *
    * $UnsetEnvVar, `userHome + separator + ".config"` is chosen as the default.
    *
    * @see [[XDG_CONFIG_HOME]]
    */
  def xdgConfigHome = envOrElse(XDG_CONFIG_HOME, userHome + separator + ".config")

  /** Returns the base directory for user-specific data files.
    *
    * $UnsetEnvVar, `userHome + separator + ".local" + separator + "share"` is chosen as the
    * default.
    *
    * @see [[XDG_DATA_HOME]]
    */
  def xdgDataHome = envOrElse(XDG_DATA_HOME, userHome + separator + ".local" + separator + "share")

  /** Returns the base directory for user-specific runtime files and other file objects.
    *
    * $UnsetEnvVar, `tmpDir + separator + userName` is chosen as the default.
    *
    * @see [[XDG_RUNTIME_DIR]]
    */
  def xdgRuntimeDir = envOrElse(XDG_RUNTIME_DIR, tmpDir + separator + userName)

  // -----------------------------------------------------------------------------------------------
  // preference ordered (global) base directories
  // -----------------------------------------------------------------------------------------------

  /** Returns the distinct, global, preference ordered base directories relative to which
    * configuration files are searched.
    *
    * $UnsetEnvVar, `Seq("/etc/xdg", "/etc")` is chosen as the default.
    *
    * @see [[XDG_CONFIG_DIRS]]
    */
  def xdgGlobalConfigDirs: Seq[String] = envOrNone(XDG_CONFIG_DIRS) map {
    _.split(pathSeparator).toSeq.distinct
  } getOrElse {
    Seq("/etc/xdg", "/etc")
  }

  /** Returns the distinct, global, preference ordered base directories relative to which data files
    * are searched.
    *
    * $UnsetEnvVar, `Seq("/usr/local/share", "/usr/share")` is chosen as the default.
    *
    * @see [[XDG_DATA_DIRS]]
    */
  def xdgGlobalDataDirs: Seq[String] = envOrNone(XDG_DATA_DIRS) map {
    _.split(pathSeparator).toSeq.distinct
  } getOrElse {
    Seq("/usr/local/share", "/usr/share")
  }

  // -----------------------------------------------------------------------------------------------
  // preference ordered base directories (including user-specific)
  // -----------------------------------------------------------------------------------------------

  /** Returns the distinct, preference ordered base directories relative to which configuration
    * files are searched.
    */
  def xdgConfigDirs: Seq[String] = (xdgConfigHome +: xdgGlobalConfigDirs).distinct

  /** Returns the distinct, preference ordered base directories relative to which data files are
    * searched.
    */
  def xdgDataDirs: Seq[String] = (xdgDataHome +: xdgGlobalDataDirs).distinct

}
