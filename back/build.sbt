// Dependencies
lazy val scalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
lazy val bcrypt    = "com.github.t3hnar"      %% "scala-bcrypt"       % "4.1"
lazy val jwt       = "com.github.jwt-scala"   %% "jwt-play"           % "7.1.4"

// database deps
lazy val postgresqlDriver = "org.postgresql"  % "postgresql"                    % "42.2.19"
lazy val scalike          = "org.scalikejdbc" %% "scalikejdbc"                  % "3.5.0"
lazy val scalikeConf      = "org.scalikejdbc" %% "scalikejdbc-config"           % "3.5.0"
lazy val scalikePlay      = "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5"

// logging libs
lazy val logback      = "ch.qos.logback"             % "logback-classic" % "1.2.3"
lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.3"

// cache libs
lazy val caffeine = "com.github.ben-manes.caffeine" % "caffeine" % "2.8.8"

// kbs libs
lazy val kieApi               = "org.kie"    % "kie-api"               % "7.53.1.Final" % "provided"
lazy val droolsCore           = "org.drools" % "drools-core"           % "7.53.1.Final"
lazy val droolsCompiler       = "org.drools" % "drools-compiler"       % "7.53.1.Final"
lazy val droolsTraits         = "org.drools" % "drools-traits"         % "7.53.1.Final"
lazy val droolsDecisionTables = "org.drools" % "drools-decisiontables" % "7.53.1.Final"
lazy val droolsTemplates      = "org.drools" % "drools-templates"      % "7.53.1.Final"
lazy val kieInternal          = "org.kie"    % "kie-internal"          % "7.53.1.Final"

// utils
lazy val jodaTime = "joda-time" % "joda-time" % "2.10.10"

lazy val droolsLibs: Seq[ModuleID] = Seq(
  kieApi,
  droolsCore,
  droolsCompiler,
  droolsTraits,
  droolsDecisionTables,
  droolsTemplates,
  kieInternal
)

lazy val dependencies = Seq(
  guice,
  scalaTest,
  postgresqlDriver,
  "com.typesafe.play" %% "play-slick"            % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  bcrypt,
  jwt,
  evolutions,
  scalike,
  scalikeConf,
  scalikePlay,
  logback,
  scalaLogging,
  caffeine,
  jodaTime
) ++ droolsLibs

// Project conf
lazy val root = (project in file("."))
  .enablePlugins(PlayScala, PlayJava, PlayEbean)
  .settings(
    name := """back""",
    organization := "io.recipe",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.5",
    libraryDependencies ++= dependencies
  )
