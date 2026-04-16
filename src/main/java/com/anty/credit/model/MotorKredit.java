package com.anty.credit.model;

public class MotorKredit implements CreditProduct {
    public static final String TYPE = "motor";
    public static final double INTEREST_RATE = 0.09;
    public static final double MINIMUM_DP = 0.25;
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
        return "Kredit Motor - Bunga " + (INTEREST_RATE * 100) + "% - DP minimal " + (MINIMUM_DP * 100) + "%";
    }
}
