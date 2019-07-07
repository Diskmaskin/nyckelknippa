#!/bin/bash

CLASSPATH=".:../resources/*"

cd src
echo "Compiling"
javac -d ../bin -cp $CLASSPATH nyckelknippa/main/Main.java
echo "Compilation complete"
cd ../bin
echo "Running"
java nyckelknippa.main.Main
cd ..
echo "Terminated"
