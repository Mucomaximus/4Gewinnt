name := "de.htwg.se.vierGewinnt"

version := "0.1"

scalaVersion := "2.13.5"


libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"

libraryDependencies += "com.google.inject" % "guice" % "5.0.1"
libraryDependencies += "com.google.inject.extensions" % "guice-assistedinject" % "5.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"

coverageExcludedPackages := ".*gui.*"