package com.anty.credit.service;

import java.io.*;
import java.util.*;

public class FileInputService {

    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException("Gagal membaca file: " + e.getMessage());
        }

        return lines;
    }
}