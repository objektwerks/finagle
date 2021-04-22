name := "finagle"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.5"
libraryDependencies ++= {
  val twitterVersion = "21.2.0"
  Seq(
    "com.twitter" %% "finagle-http" % twitterVersion,
    "com.twitter" %% "finagle-thrift" % twitterVersion,
    "com.twitter" %% "scrooge-core" % twitterVersion,
    "org.apache.thrift" % "libthrift" % "0.13.0",
    "com.typesafe" % "config" % "1.4.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalatest" %% "scalatest" % "3.2.8" % Test
  )
}
