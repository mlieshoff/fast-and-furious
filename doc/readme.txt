Backup system which is able to cipher and decipher backup files automatically with lightweight environment.

= What is needed? = 

  * Java 7
  * Ant, at least 1.8.2
  * ant-contrib (http://ant-contrib.sourceforge.net/) in version 1.0b3

= How to install? =

Just unzip the zip to a folder you want. 

= How it works? =

fast-and-furious works supports creating backups and restoring backups. 

== Creating backups (target backup)==

Backup files will be zipped automatically in "${java.io.tmpdir}/backup" directory and ciphered with DES encryption in "backup.save.dir" directory. This directory can by any kind of directory, perhaps a cloud folder or something else.

{{{
    <property name="backup.save.dir" value="/home/user/Ubuntu One/backups"/>
}}}

== Restoring backups (target backdown) ==

Ciphered backup files in "backup.save.dir" folder will be deciphered in "${java.io.tmpdir}/backup" directory. Then backup files are now ready to use (unzip).

= How to configure? = 

Copy the sample.xml to a new file and edit it to configure.

== Set another temporary directory ==

Put 

{{{-Djava.io.tmpdir=/path/to/tmpdir}}}

into your ant call.

= How to run manually? = 

Just call Ant on the sample.xml file you copied.

{{{ant -f sample.xml backup}}}

{{{ant -f sample.xml backdown}}}

= How to run in Linux/CRON? =

In folders "linux_crontab" and "start_in_cron" are examples to use. You can write a simple Shell-Script the executes Ant in defined directory.
