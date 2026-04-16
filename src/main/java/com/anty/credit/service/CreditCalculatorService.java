package com.anty.credit.service;

import com.anty.credit.model.Loan;

public class CreditCalculatorService {

    public double calculate(Loan loan, double rate) {
        double principal = loan.getAmount() - loan.getDp();
        double total = principal + (principal * rate * loan.getTenor());
        return total / (loan.getTenor() * 12);
    }
}