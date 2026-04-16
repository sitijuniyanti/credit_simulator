package com.anty.credit.model;

public class LoanCalculation {
    private double monthlyPayment;
    private double totalPayment;
    private double totalInterest;
    private int numberOfPayments;
    private int year;
    private double interestRate;
    
    public LoanCalculation(double monthlyPayment, double totalPayment, 
                          double totalInterest, int numberOfPayments,
                          int year, double interestRate) {
        this.monthlyPayment = monthlyPayment;
        this.totalPayment = totalPayment;
        this.totalInterest = totalInterest;
        this.numberOfPayments = numberOfPayments;
        this.year = year;
        this.interestRate = interestRate;
    }
    
    public double getMonthlyPayment() { return monthlyPayment; }
    public double getTotalPayment() { return totalPayment; }
    public double getTotalInterest() { return totalInterest; }
    public int getNumberOfPayments() { return numberOfPayments; }
    public int getYear() { return year; }
    public double getInterestRate() { return interestRate; }
}
