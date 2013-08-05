import java.lang.String

scalaVersion := "2.10.2"

organization := "com.sksamuel.scrimage"

name := "scrimage-core"

version := "1.3.4"

publishMavenStyle := true

publishTo <<= version {
  (v: String) =>
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

libraryDependencies ++= Seq(
  "org.apache.sanselan" % "sanselan" % "0.97-incubator",
  "log4j" % "log4j" % "1.2.17",
  "org.slf4j" % "slf4j-log4j12" % "1.6.6",
  "org.slf4j" % "slf4j-api" % "1.6.6",
  "commons-io" % "commons-io" % "2.4",
  "org.scalatest" % "scalatest_2.10" % "1.9.1",
  "org.imgscalr" % "imgscalr-lib" % "4.2" % "test",
  "junit" % "junit" % "4.11" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test"
)
