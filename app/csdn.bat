path %path% 
SET APP_HOME=D:\chenyb\myproject\finally\sina1.0\app
SET APP_CLASSES=%APP_HOME%\classes
SET APP_LIB=%APP_HOME% 
cd %APP_CLASSES%
java -Xmx256m -Djava.ext.dirs=%APP_HOME%\lib com.app.csdn.RunnerMain  
pause 