name := "apps"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.webjars" % "bootstrap" % "3.0.0",
  "org.webjars" %% "webjars-play" % "2.2.0"
)     

play.Project.playJavaSettings

resolvers += "webjars" at "http://webjars.github.com/m2"