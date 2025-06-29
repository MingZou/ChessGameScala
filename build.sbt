name := "ChessGame"

version := "0.1"

scalaVersion := "2.13.13"



Compile / unmanagedJars += baseDirectory.value / "lib" / "userinput.jar"
Compile / mainClass := Some("chess.Main")

enablePlugins(AssemblyPlugin)