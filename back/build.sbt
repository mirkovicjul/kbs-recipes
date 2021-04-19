// Dependencies
lazy val scalaTest        = "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// database deps
lazy val postgresqlDriver = "org.postgresql"         % "postgresql"          % "42.2.19"
lazy val scalike     = "org.scalikejdbc" %% "scalikejdbc"                  % "3.5.0"
lazy val scalikeConf = "org.scalikejdbc" %% "scalikejdbc-config"           % "3.5.0"
lazy val scalikePlay = "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5"

lazy val dependencies = Seq(
  guice,
  scalaTest,
  postgresqlDriver,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  evolutions
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
