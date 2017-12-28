package org.lxp.java8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudyFileTest {
    private final String tmpPath = "d:/tmp";
    private File tmp;
    private File file;

    @Before
    public void setUp() throws Exception {
        tmp = new File(tmpPath);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        file = new File(tmpPath, "1.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
            IntStream.rangeClosed(1, 3).forEach(i -> {
                try {
                    String s = String.valueOf(i);
                    writer.append(s).append("\r\n").append(s).append("\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @After
    public void tearDown() {
        file.delete();
        tmp.delete();
    }

    @Test
    public void testRead() throws Exception {
        Assert.assertEquals("[1, 1, 2, 2, 3, 3]", StudyFile.read(file).toString());
    }

    @Test
    public void testReadDistinct() throws Exception {
        Assert.assertEquals(1, Files.list(Paths.get(tmpPath)).collect(Collectors.toList()).size());
        Assert.assertEquals("[3, 2, 1]", StudyFile.readDistinct(file).toString());
    }
}
