import de.johoop.jacoco4sbt._

name := "matryoshka-security"
organization := "com.innoq"
description := "Helper for using Spring Security behind a load balancer or reverse proxy"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.2"

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := (_ => false)
startYear := Some(2017)
organizationHomepage := Some(url("https://innoq.com"))
homepage := Some(url("https://github.com/innoq/matryoshka-security"))
developers := List(
  Developer("dwestheide", "Daniel Westheide", "", url("https://github.com/dwestheide")))
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/innoq/matryoshka-security"),
  connection = "scm:git:git@github.com:innoq/matryoshka-security.git"
))
publishTo := Some(
  if (isSnapshot.value) Opts.resolver.sonatypeSnapshots
  else Opts.resolver.sonatypeStaging
)
crossPaths := false
autoScalaLibrary := false

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.springframework" % "spring-web" % "4.3.10.RELEASE",
  "org.springframework" % "spring-test" % "4.3.10.RELEASE" % "test",
  "org.scalatest" %% "scalatest" % "3.0.3" % "test"
)

jacoco.settings

jacoco.reportFormats in jacoco.Config := Seq(
  XMLReport(encoding = "utf-8"),
  ScalaHTMLReport(withBranchCoverage = true))
