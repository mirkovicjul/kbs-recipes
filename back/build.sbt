// Dependencies
lazy val scalaTest        = "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
lazy val bcrypt = "com.github.t3hnar" %% "scala-bcrypt" % "4.1"
lazy val jwt = "com.github.jwt-scala" %% "jwt-play" % "7.1.4"

// database deps
lazy val postgresqlDriver = "org.postgresql"         % "postgresql"          % "42.2.19"
lazy val scalike     = "org.scalikejdbc" %% "scalikejdbc"                  % "3.5.0"
lazy val scalikeConf = "org.scalikejdbc" %% "scalikejdbc-config"           % "3.5.0"
lazy val scalikePlay = "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5"

// logging libs
lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3"

lazy val dependencies = Seq(
  guice,
  scalaTest,
  postgresqlDriver,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  bcrypt,
  jwt,
  evolutions,
  scalike,
  scalikeConf,
  scalikePlay,
  logback,
  scalaLogging
)

// Project conf
lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """back""",
    organization := "io.recipe",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.5",
    libraryDependencies ++= dependencies
  )
