# crontab  00 05 20 * * * /usr/chenyb/app/copylog.sh 1>/log/1.log 2&>1    /etc/init.d/cron start
#!/bin/bash
LogPath = /chenyb/app/classes
rm -rf $LogPath/rank_`date +%Y-%m-%d`.out
cp $LogPath/rank.out  $LogPath/catalina_`date +%Y-%m-%d`.out
#tail -4000  $LOGPATH/catalina.out > $LOGPATH/catalina_`date +%Y-%m-%d`.out
cat  /dev/null >$LogPath/rank.out