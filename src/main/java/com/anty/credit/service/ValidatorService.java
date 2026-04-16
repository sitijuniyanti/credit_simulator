package com.anty.credit.service;

import java.time.Year;

public class ValidatorService {

    public static void validate(
        String type,
        String condition,
        int year,
        double amount,
        int tenor,
        double dp
    ) {

        int currentYear = Year.now().getValue();

        // 1. Tenor
        if (tenor > 6) {
            throw new IllegalArgumentException("Tenor max 6 tahun");
        }

        // 2. DP
        double dpPercent = dp / amount;

        if (condition.equalsIgnoreCase("NEW") && dpPercent < 0.35) {
            throw new IllegalArgumentException("DP NEW minimal 35%");
        }

        if (condition.equalsIgnoreCase("USED") && dpPercent < 0.25) {
            throw new IllegalArgumentException("DP USED minimal 25%");
        }

        // 3. Tahun kendaraan
        if (condition.equalsIgnoreCase("NEW") &&
            year < currentYear - 1) {
            throw new IllegalArgumentException("Tahun kendaraan NEW tidak valid");
        }
    }
    
}