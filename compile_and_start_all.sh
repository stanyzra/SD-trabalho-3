#!/bin/bash

javac -d destDir *.java
start java -classpath destDir/ -Djava.rmi.server.codebase=file:destDir/ trabalho_3.LoadBalancer
start java -classpath destDir/ -Djava.rmi.server.codebase=file:destDir/ trabalho_3.Server1
start java -classpath destDir/ -Djava.rmi.server.codebase=file:destDir/ trabalho_3.Server2