name := "finagle"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.13"
libraryDependencies ++= {
  val twitterVersion = "22.12.0"
  Seq(
    "com.twitter" %% "finagle-http" % twitterVersion,
    "com.twitter" %% "finagle-thrift" % twitterVersion,
    "com.twitter" %% "scrooge-core" % twitterVersion,
    "org.apache.thrift" % "libthrift" % "0.19.0",
    "com.typesafe" % "config" % "1.4.3",
    "ch.qos.logback" % "logback-classic" % "1.5.3",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
