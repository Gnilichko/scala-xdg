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

/** Contains the names of XDG Base Directory Specification related environment variables. This
  * object provides static access to these entities.
  *
  * The original documentation of these environment variables is located at the
  * [[http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html XDG Base Directory
  * Specification]].
  */
object XDGBaseDirEnvironment extends XDGBaseDirEnvironment

/** Contains the names of XDG Base Directory Specification related environment variables. This trait
  * provides access to these entities for mixin composition.
  *
  * The original documentation of these environment variables is located at the
  * [[http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html XDG Base Directory
  * Specification]].
  */
trait XDGBaseDirEnvironment {

  // -----------------------------------------------------------------------------------------------
  // user-specific base directories
  // -----------------------------------------------------------------------------------------------

  /** Returns the name of the environment variable that defines the base directory for user-specific
    * non-essential (cached) data.
    */
  val XDG_CACHE_HOME = "XDG_CACHE_HOME"

  /** Returns the name of the environment variable that defines the base directory for user-specific
    * configuration files.
    */
  val XDG_CONFIG_HOME = "XDG_CONFIG_HOME"

  /** Returns the name of the environment variable that defines the base directory for user-specific
    * data files.
    */
  val XDG_DATA_HOME = "XDG_DATA_HOME"

  /** Returns the name of the environment variable that defines the base directory for user-specific
    * runtime files and other file objects.
    */
  val XDG_RUNTIME_DIR = "XDG_RUNTIME_DIR"

  // -----------------------------------------------------------------------------------------------
  // preference ordered (global) base directories
  // -----------------------------------------------------------------------------------------------

  /** Returns the name of the environment variable that defines the preference ordered base
    * directories relative to which configuration files are searched.
    */
  val XDG_CONFIG_DIRS = "XDG_CONFIG_DIRS"

  /** Returns the name of the environment variable that defines the set of preference ordered base
    * directories relative to which data files are searched.
    */
  val XDG_DATA_DIRS = "XDG_DATA_DIRS"

}
