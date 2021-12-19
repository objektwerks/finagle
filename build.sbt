name := "finagle"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.7"
libraryDependencies ++= {
  val twitterVersion = "21.12.0"
  Seq(
    "com.twitter" %% "finagle-http" % twitterVersion,
    "com.twitter" %% "finagle-thrift" % twitterVersion,
    "com.twitter" %% "scrooge-core" % twitterVersion,
    "org.apache.thrift" % "libthrift" % "0.15.0",
    "com.typesafe" % "config" % "1.4.1",
    "ch.qos.logback" % "logback-classic" % "1.2.9",
    "org.scalatest" %% "scalatest" % "3.2.10" % Test
  )
}
