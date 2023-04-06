package com.util;

import java.io.File;

/**
 * Get the execution path where the Jar file is invoked
 */
public class ExecutionPath {

    /**
     * Get the parent path from execution jar location
     * @return root path to jar or invoke class
     */
    public String getDirectory() {

        String path = ExecutionPath.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();

        return new File(path).getParentFile().getAbsolutePath();

    }

}
