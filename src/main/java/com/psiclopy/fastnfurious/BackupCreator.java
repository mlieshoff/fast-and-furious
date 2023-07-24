package com.psiclopy.fastnfurious;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;

public class BackupCreator {

  private File backupTargetDirectory;
  private File tmpDirectory;
  private File tmpCipherDirectory;

  public void start(String backupTargetDirectoryParam, List<String> sourceDirectoriesParam) throws IOException {
    backupTargetDirectory = new File(backupTargetDirectoryParam);
    if (!backupTargetDirectory.exists() && (!backupTargetDirectory.mkdirs())) {
      throw new IllegalStateException("couldn't create backup directory: " + backupTargetDirectory.getPath());
    }
    tmpDirectory = Files.createTempDirectory("fastnfurious_tmp").toFile();
    tmpCipherDirectory = Files.createTempDirectory("fastnfurious_cipher").toFile();
    ZipArchiveOutputStream
        zipArchiveOutputStream =
        new ZipArchiveOutputStream(new File(backupTargetDirectory, "backup.zip"));
    for (String sourceDirectoryName : sourceDirectoriesParam) {
      File sourceDirectory = new File(sourceDirectoryName);
      if (!sourceDirectory.exists()) {
        System.out.println("skip");
      } else {
        for (File file : FileUtils.listFiles(sourceDirectory, null, true)) {
          System.out.println("zip " + file);
          if (!file.isDirectory()) {
            System.out.println(file.getPath());
            ZipArchiveEntry entry = new ZipArchiveEntry(file, file.getName());
            entry.setSize(file.length());
            zipArchiveOutputStream.putArchiveEntry(entry);
            zipArchiveOutputStream.write(FileUtils.readFileToByteArray(file));
            zipArchiveOutputStream.closeArchiveEntry();
          }
        }
      }
    }
    zipArchiveOutputStream.flush();
    zipArchiveOutputStream.close();
    if (!tmpDirectory.delete()) {
      throw new IllegalStateException("couldn't delete tmp directory: " + tmpDirectory.getPath());
    }
    if (!tmpCipherDirectory.delete()) {
      throw new IllegalStateException("couldn't delete tmp directory: " + tmpDirectory.getPath());
    }
  }
}
