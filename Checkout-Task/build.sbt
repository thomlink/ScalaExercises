ThisBuild / scalaVersion := "2.12.8"
ThisBuild / organization := "com.tomlinker"

lazy val hello = (project in file("."))
  .settings(
    name := "Checkout",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.0"
  )
