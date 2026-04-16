package com.anty.credit.service;

import com.anty.credit.model.Loan;
import com.anty.credit.model.LoanCalculation;
import com.anty.credit.model.CreditProduct;

public class CreditCalculatorService {

    public LoanCalculation calculate(Loan loan, CreditProduct product) {
        double principal = loan.getAmount() - loan.getDp();
        double rate = product.getInterestRate();
        int tenor = loan.getTenor();
        
        double totalWithInterest = principal + (principal * rate * tenor);
        double monthlyPayment = totalWithInterest / (tenor * 12);
        int numberOfPayments = tenor * 12;
        double totalInterest = (principal * rate * tenor);
        
        return new LoanCalculation(
            monthlyPayment,
            totalWithInterest,
            totalInterest,
            numberOfPayments,
            tenor,
            rate
        );
    }
}