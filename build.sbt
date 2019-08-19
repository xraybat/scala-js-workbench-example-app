enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.8"

scalacOptions ++= Seq("-P:scalajs:suppressExportDeprecations")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.7",
  "com.lihaoyi" %%% "scalatags" % "0.7.0"
)
