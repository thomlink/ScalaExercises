ThisBuild / scalaVersion := "2.12.8"
ThisBuild / organization := "com.tomlinker"

lazy val hello = (project in file("."))
  	.settings(
    	name := "Checkout",
    	libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  	)