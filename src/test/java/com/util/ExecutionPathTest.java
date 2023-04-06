package com.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.java.Log;

@Log
public class ExecutionPathTest {

    private ExecutionPath executionPath;

    @Before
    public void setUp() {
        executionPath = new ExecutionPath();
    }

    @Test
    public void testGetDirectory() {

        Path path = Paths.get(executionPath.getDirectory());
        log.info(path.toString());
        Assert.assertTrue(new File(path.toString()).isDirectory());

    }
}
