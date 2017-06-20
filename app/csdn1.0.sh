#
#!/bin/sh  
RUNNING_USER=root  
APP_HOME=/chenyb/app  
APP_MAINCLASS=com.app.csdn.RunnerMain  
APPCLASSPATH="$APP_HOME/"classes  
#for i in "$APP_HOME"/lib/*.jar; do  
 #  APPCLASSPATH="$APPCLASSPATH":"$i"  
#done  
JAVA_OPTS="-ms512m -mx512m -Xmn256m -Djava.awt.headless=true -XX:MaxPermSize=128m"  
cd $APPCLASSPATH
#JAVA_CMD="nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $APPCLASSPATH $APP_MAINCLASS >$APP_HOME/myout.log 2>&1 &"  
java -Xmx256m -Djava.ext.dirs="$APP_HOME"\lib $APP_MAINCLASS  
#su - $RUNNING_USER -c "$JAVA_CMD" 