# Access to [freedesktop.org][xdg] Standards for [Scala][scala]

`scala-xdg` is a set of libraries for the programming language [Scala][scala] to access the
standards defined by [freedesktop.org][xdg] (formerly X Desktop Group).

## Sub-Projects

This project is organized in several sub-projects which are described in the following sections.
These sub-projects can be included as a dependency using [SBT][sbt] by adding
`"org.freedesktop" %% "<sub-project-name>" % "<tagged-version>"` to the library dependencies of the
build definition.

### [XDG Base Directory Specification][basedir]

The sub-project `scala-xdg-basedir` contains the implementation of the [XDG Base Directory
Specification][basedir].


[xdg]: http://www.freedesktop.org/
[scala]: http://scala-lang.org/
[sbt]: http://www.scala-sbt.org/
[basedir]: http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html


---

[![endorse](http://api.coderwall.com/wookietreiber/endorsecount.png)](http://coderwall.com/wookietreiber)

