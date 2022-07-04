name := "googlePlayStore"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq("org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.vegas-viz" %% "vegas" % "0.3.9",
  "org.vegas-viz" %% "vegas-spark" % "0.3.9",
  "org.scala-lang.modules" %% "scala-swing" % "2.1.1"
)