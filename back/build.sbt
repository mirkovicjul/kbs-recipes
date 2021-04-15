
lazy val scalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

lazy val dependencies = Seq(
  guice,
  scalaTest
)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """back""",
    organization := "io.recipe",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.5",
    libraryDependencies ++= dependencies
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "io.recipe.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "io.recipe.binders._"
