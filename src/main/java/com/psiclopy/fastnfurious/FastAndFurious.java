package com.psiclopy.fastnfurious;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class FastAndFurious {

  private static final Options options = new Options()
      .addRequiredOption("t", "backup-target-directory", true, "backup target directory")
      .addRequiredOption("s", "source-directories", true, "comma-separated source directories");

  public static void main(String[] args) {
    CommandLineParser commandLineParser = new DefaultParser();
    try {
      CommandLine commandLine = commandLineParser.parse(options, args);
      if (commandLine.hasOption("help")) {
        printHelp();
      }
      String backupDirectory = commandLine.getOptionValue("t");
      String[] sourceDirectories = commandLine.getOptionValue("s").split(",");
      BackupCreator backupCreator = new BackupCreator();
      backupCreator.start(backupDirectory, sourceDirectories);
    } catch (ParseException e) {
      printHelp();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void printHelp() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(FastAndFurious.class.getSimpleName(), options);
  }

}