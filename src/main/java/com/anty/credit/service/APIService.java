package com.anty.credit.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIService {

    private static final int CONNECTION_TIMEOUT = 5000; // 5 detik
    private static final int READ_TIMEOUT = 5000; // 5 detik

    public static String getData(String urlString) {
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP Error: " + responseCode + " - " + conn.getResponseMessage());
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }

            conn.disconnect();

        } catch (Exception e) {
            throw new RuntimeException("Gagal call API: " + e.getMessage(), e);
        }

        return result.toString();
    }
}