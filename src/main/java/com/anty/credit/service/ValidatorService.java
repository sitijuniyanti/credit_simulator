package com.anty.credit.service;

import java.time.Year;
import com.anty.credit.model.CreditProduct;
import com.anty.credit.model.Loan;

public class ValidatorService {

    // Inner data class untuk menyimpan credit data yang sudah valid
    public static class CreditData {
        public String type;
        public String condition;
        public int year;
        public double amount;
        public int tenor;
        public double dp;

        public CreditData(String type, String condition, int year, double amount, int tenor, double dp) {
            this.type = type;
            this.condition = condition;
            this.year = year;
            this.amount = amount;
            this.tenor = tenor;
            this.dp = dp;
        }
    }

    // ===== VALIDATION METHODS =====
    public static void validate(
            String type,
            String condition,
            int year,
            double amount,
            int tenor,
            double dp) {
        validateType(type);
        validateCondition(condition);
        validateYear(year, condition);
        validateAmount(amount);
        validateTenor(tenor);
        validateDP(dp, amount, condition);
    }

    public static boolean isValidType(String type) {
        return type.matches("[a-zA-Z]+") &&
                (type.equalsIgnoreCase("mobil") || type.equalsIgnoreCase("motor"));
    }

    public static boolean isValidCondition(String condition) {
        return condition.equalsIgnoreCase("NEW") || condition.equalsIgnoreCase("USED");
    }

    public static boolean isValidYear(int year, String condition) {
        int currentYear = Year.now().getValue();
        
        // 1. Validasi 4 digit angka
        if (year < 1000 || year > 9999) {
            throw new IllegalArgumentException("Tahun harus 4 digit angka");
        }
        
        // 2. Validasi khusus berdasarkan kondisi
        if (condition.equalsIgnoreCase("NEW")) {
            // NEW: currentYear - 1 sampai currentYear
            if (year < currentYear - 1 || year > currentYear) {
                throw new IllegalArgumentException("Kendaraan NEW minimal tahun " + (currentYear - 1));
            }
        } else if (condition.equalsIgnoreCase("USED")) {
            // USED: currentYear - 5 sampai currentYear
            if (year < currentYear - 5 || year > currentYear) {
                throw new IllegalArgumentException("Tahun kendaraan USED harus antara " + (currentYear - 5) + " - " + currentYear);
            }
        }
        
        return true;
    }

    public static void validateType(String type) {
        if (!type.equalsIgnoreCase("mobil") && !type.equalsIgnoreCase("motor")) {
            throw new IllegalArgumentException("Jenis kendaraan harus mobil atau motor");
        }
    }

    public static void validateCondition(String condition) {
        if (!condition.equalsIgnoreCase("NEW") && !condition.equalsIgnoreCase("USED")) {
            throw new IllegalArgumentException("Kondisi kendaraan harus NEW atau USED");
        }
    }

    public static void validateYearMethod(int year, String condition) {
        isValidYear(year, condition);
    }

    private static void validateYear(int year, String condition) {
        isValidYear(year, condition);
    }

    // Validate Loan with CreditProduct rules
    public static void validateLoan(Loan loan, CreditProduct product) {
        double dpPercent = loan.getDp() / loan.getAmount();
        
        // Validate DP percentage
        if (dpPercent < product.getMinimumDPPercentage()) {
            throw new IllegalArgumentException(
                "DP " + product.getType() + " minimal " + (product.getMinimumDPPercentage() * 100) + "% dari jumlah pinjaman"
            );
        }
        
        // Validate tenor
        if (loan.getTenor() > product.getMaxTenor()) {
            throw new IllegalArgumentException(
                "Tenor untuk " + product.getType() + " maksimal " + product.getMaxTenor() + " tahun"
            );
        }
    }

    public static void validateTenor(int tenor) {
        if (tenor < 1 || tenor > 6) {
            throw new IllegalArgumentException("Tenor pinjaman harus antara 1-6 tahun");
        }
    }

    public static void validateAmount(double amount) {
        if (amount <= 0 || amount > 1_000_000_000) {
            throw new IllegalArgumentException("Jumlah pinjaman harus antara 1 - 1 miliar");
        }
    }

    public static void validateDP(double dp, double amount, String condition) {
        if (dp <= 0) {
            throw new IllegalArgumentException("Down payment harus lebih dari 0");
        }
        
        if (dp >= amount) {
            throw new IllegalArgumentException("Down payment tidak boleh melebihi jumlah pinjaman");
        }
        
        double dpPercent = dp / amount;
        if (condition.equalsIgnoreCase("NEW") && dpPercent < 0.35) {
            throw new IllegalArgumentException("DP NEW minimal 35% dari jumlah pinjaman");
        }
        
        if (condition.equalsIgnoreCase("USED") && dpPercent < 0.25) {
            throw new IllegalArgumentException("DP USED minimal 25% dari jumlah pinjaman");
        }
    }

}