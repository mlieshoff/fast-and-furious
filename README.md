# fast-and-furious

Backup system which is able to cipher and decipher backup files automatically with lightweight environment.

## CI

https://travis-ci.org/mlieshoff/fast-and-furious

## What is needed?

  * Java 7
  * Ant, at least 1.8.2
  * ant-contrib (http://ant-contrib.sourceforge.net/) in version 1.0b3

## How to install?

Just unzip the zip to a folder you want. 

## How it works?

fast-and-furious works supports creating backups and restoring backups. 

### Creating backups (target backup)

Backup files will be zipped automatically in "${java.io.tmpdir}/backup" directory and ciphered with DES encryption in "backup.save.dir" directory. This directory can by any kind of directory, perhaps a cloud folder or something else.

<code>
    <!property name="backup.save.dir" value="/home/user/Ubuntu One/backups"/!>
</code>

### Restoring backups (target backdown)

Ciphered backup files in "backup.save.dir" folder will be deciphered in "${java.io.tmpdir}/backup" directory. Then backup files are now ready to use (unzip).

## How to configure? 

Copy the sample.xml to a new file and edit it to configure.

### Set another temporary directory

Put 

<code>-Djava.io.tmpdir=/path/to/tmpdir</code>

into your ant call.

## How to run manually? 

Just call Ant on the sample.xml file you copied.

<code>ant -f sample.xml backup</code>

<code>ant -f sample.xml backdown</code>

## How to run in Linux/CRON?

In folders "linux_crontab" and "start_in_cron" are examples to use. You can write a simple Shell-Script the executes Ant in defined directory.
