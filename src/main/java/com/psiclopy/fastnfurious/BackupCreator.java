package com.psiclopy.fastnfurious;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class BackupCreator {

  private File backupTargetDirectory;
  private File tmpDirectory;
  private File tmpCipherDirectory;

  public void start(String backupTargetDirectoryParam, String[] sourceDirectoriesParam) throws IOException {
    backupTargetDirectory = new File(backupTargetDirectoryParam);
    tmpDirectory = new File(FileUtils.getTempDirectory(), "fastnfurious");
    FileUtils.forceMkdir(backupTargetDirectory);
    FileUtils.forceMkdir(tmpDirectory);
    tmpCipherDirectory = new File(tmpDirectory, "cipher");
    for (String sourceDirectoryParam : sourceDirectoriesParam) {
      File sourceDirectory = new File(sourceDirectoryParam);
      if (sourceDirectory.exists()) {

      } else {
        System.out.println("source directory not existing, skipping it... " + sourceDirectory);
      }
    }
  }
}
