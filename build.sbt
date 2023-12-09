name := "finagle"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.12"
libraryDependencies ++= {
  val twitterVersion = "22.12.0"
  Seq(
    "com.twitter" %% "finagle-http" % twitterVersion,
    "com.twitter" %% "finagle-thrift" % twitterVersion,
    "com.twitter" %% "scrooge-core" % twitterVersion,
    "org.apache.thrift" % "libthrift" % "0.19.0",
    "com.typesafe" % "config" % "1.4.2",
    "ch.qos.logback" % "logback-classic" % "1.4.14",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
}
