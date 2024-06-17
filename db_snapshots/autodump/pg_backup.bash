# ~/pg_backup.sh
db_name=terrestrial_tutor
db_user=tutor
db_host=localhost
backupfolder=/dumps/backups
# Сколько дней хранить файлы
keep_day=7
sqlfile=$backupfolder/database-$(date +%d-%m-%Y_%H-%M-%S).sql
zipfile=$backupfolder/database-$(date +%d-%m-%Y_%H-%M-%S).zip
mkdir -p $backupfolder

if pg_dump -U $db_user -h $db_host $db_name > $sqlfile ; then
   echo 'Sql dump created' > /dumps_logs
else
   echo 'pg_dump return non-zero code' > /dumps_logs 
   exit
fi

if gzip -c $sqlfile > $zipfile; then
   echo 'The backup was successfully compressed' > /dumps_logs
else
   echo 'Error compressing backup' > /dumps_logs
   exit
fi
rm $sqlfile
echo $zipfile

find $backupfolder -mtime +$keep_day -delete
