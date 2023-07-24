package com.psiclopy.fastnfurious;

import static com.psiclopy.fastnfurious.FastAndFurious.main;
import static org.apache.commons.io.FileUtils.listFiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FastAndFuriousIntegrationTest {

  @TempDir
  File baseDir;

  @Test
  void test_backupsAndSourceNotExisting_thenCreateBackupsDirAndSkipSources() {
    File backupDir = new File(baseDir, "backups");
    File source1Dir = new File(baseDir, "source1");
    File source2Dir = new File(baseDir, "source2");

    main(new String[]{
        "-t " + backupDir.getPath(),
        "-s " + source1Dir.getPath() + "," + source2Dir.getPath()
    });

    assertThat(backupDir).exists();
    assertThat(listFiles(backupDir, new String[]{"*"}, true)).isEmpty();
  }

  @Test
  void test_sourcesExisting_thenZipFiles() throws IOException {
    File backupDir = new File(baseDir, "backups");
    File source1Dir = new File(baseDir, "source1");
    File source1Sub = new File(source1Dir, "sub");
    source1Sub.mkdirs();
    File source1File1 = new File(source1Sub, "test.txt");
    FileUtils.writeByteArrayToFile(source1File1, "lalala".getBytes(StandardCharsets.UTF_8));
    File source2Dir = new File(baseDir, "source2");

    main(new String[]{
        "-t " + backupDir.getPath(),
        "-s " + source1Dir.getPath() + "," + source2Dir.getPath()
    });

    assertThat(backupDir).exists();
    assertThat(listFiles(backupDir, new String[]{"zip"}, true)).isNotEmpty();
  }

}