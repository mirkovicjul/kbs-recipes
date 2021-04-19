// Dependencies
lazy val scalaTest          = "org.scalatestplus.play" %% "scalatestplus-play"           % "5.0.0" % Test
lazy val postgresqlDriver   = "org.postgresql"         % "postgresql"                    % "42.2.19"

lazy val dependencies = Seq(
  guice,
  scalaTest,
  postgresqlDriver
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
