package com.myProject.utilities;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtils {

    public static void writeToCSV(String filePath, Set<String> links) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (String link : links) {
                writer.write(link);
                writer.newLine();
            }
        }
    }

    public static Set<String> readLinksFromCSV(String filePath) throws IOException {
        List<String> links = Files.readAllLines(Paths.get(filePath));
        return new HashSet<>(links);
    }
}

