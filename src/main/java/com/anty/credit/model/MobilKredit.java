package com.anty.credit.model;

public class MobilKredit implements CreditProduct {
    public static final String TYPE = "mobil";
    public static final double INTEREST_RATE = 0.08;
    public static final double MINIMUM_DP = 0.35;
    public static final int MAX_TENOR = 6;
    
    @Override
    public String getType() {
        return TYPE;
    }
    
    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }
    
    @Override
    public double getMinimumDPPercentage() {
        return MINIMUM_DP;
    }
    
    @Override
    public int getMaxTenor() {
        return MAX_TENOR;
    }
    
    @Override
    public String getDescription() {
        return "Kredit Mobil - Bunga " + (INTEREST_RATE * 100) + "% - DP minimal " + (MINIMUM_DP * 100) + "%";
    }
}
