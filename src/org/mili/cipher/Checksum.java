package org.mili.cipher;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.Resource;

import java.io.File;

public class Checksum extends MatchingTask {

    private File backUpDir;
    private File baseDir;

    private String filename;
    private String resultProperty;

    public void execute() {
        File backupFile = new File(backUpDir, filename);
        long lastUpdateTime = backupFile.exists() ? backupFile.lastModified() : 0;
        boolean doBackup = false;
        DirectoryScanner directoryScanner = getDirectoryScanner(baseDir);
        for (String actualFilename : directoryScanner.getIncludedFiles()) {
            Resource resource = directoryScanner.getResource(actualFilename);
            if (resource.getLastModified() > lastUpdateTime) {
                doBackup = true;
            }
        }
        if (doBackup) {
            getProject().setProperty(resultProperty, "1");
        } else {
            getProject().setProperty(resultProperty, "-1");
        }
    }

    public void setBackUpDir(String backUpDir) {
        this.backUpDir = new File(backUpDir);
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = new File(baseDir);
    }

    public void setResultProperty(String resultProperty) {
        this.resultProperty = resultProperty;
    }

}
