package com.anty.credit.util;

public class JsonHelper {

    public static String getValue(String json, String key) {
        // Cari posisi key
        String pattern = "\"" + key + "\":";
        int patternIndex = json.indexOf(pattern);
        
        if (patternIndex == -1) {
            throw new RuntimeException("Key '" + key + "' tidak ditemukan dalam JSON");
        }
        
        int start = patternIndex + pattern.length();
        
        // Skip whitespace
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) {
            start++;
        }
        
        int end = start;
        char firstChar = json.charAt(start);
        
        // Jika value adalah string (dimulai dengan quote)
        if (firstChar == '"') {
            start++;
            end = json.indexOf("\"", start);
            if (end == -1) {
                throw new RuntimeException("Invalid JSON: unterminated string for key '" + key + "'");
            }
        } else if (firstChar == '{' || firstChar == '[') {
            // Handle object atau array
            int depth = 1;
            end = start + 1;
            while (end < json.length() && depth > 0) {
                char c = json.charAt(end);
                if (c == firstChar) depth++;
                else if ((firstChar == '{' && c == '}') || (firstChar == '[' && c == ']')) depth--;
                end++;
            }
        } else {
            // Number atau boolean
            while (end < json.length() && json.charAt(end) != ',' && json.charAt(end) != '}' && json.charAt(end) != ']') {
                end++;
            }
        }
        
        return json.substring(start, end).trim();
    }
}