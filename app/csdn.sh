#!/bin/sh  
cd /chenyb/app/classes
RUNNING_USER=root  
APP_HOME=/chenyb/app  
APP_MAINCLASS=com.app.csdn.RunnerMain  
APPCLASSPATH="$APP_HOME/"classes
cd $APPCLASSPATH
pwd
java -Xmx256m -Djava.ext.dirs=/chenyb/app/lib  $APP_MAINCLASS
