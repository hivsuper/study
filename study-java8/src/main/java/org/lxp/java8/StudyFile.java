package org.lxp.java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudyFile {
    public static List<String> read(File file) throws IOException {
        List<String> list = Collections.emptyList();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            list = reader.lines().collect(Collectors.toList());
        }
        return list;
    }

    public static List<String> readDistinct(File file) throws IOException {
        List<String> list = Collections.emptyList();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            list = reader.lines().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }
        return list;
    }
}
