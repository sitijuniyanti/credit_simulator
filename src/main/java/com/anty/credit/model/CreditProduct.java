package com.anty.credit.model;

public interface CreditProduct {
    String getType();
    
    double getInterestRate();
    
    double getMinimumDPPercentage();
    
    int getMaxTenor();
    
    String getDescription();
}
