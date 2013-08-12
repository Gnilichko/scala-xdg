import org.freedesktop.build._

lazy val root = (
  XDGProject("scala-xdg", ".")
  settings(sbtunidoc.Plugin.unidocSettings: _*)
  settings(
    scalacOptions in (Compile, doc) ++=
      Seq("-sourcepath", baseDirectory.value.getAbsolutePath, "-doc-source-url",
        "https://github.com/wookietreiber/scala-xdg/tree/master€{FILE_PATH}.scala")
  )
  aggregate(basedir)
)

lazy val basedir = (
  XDGProject("scala-xdg-basedir", "basedir")
)
