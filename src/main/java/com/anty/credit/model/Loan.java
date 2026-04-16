package com.anty.credit.model;

public class Loan {
    private double amount;
    private int tenor;
    private double dp;

    public Loan(double amount, int tenor, double dp) {
        this.amount = amount;
        this.tenor = tenor;
        this.dp = dp;
    }

    public double getAmount() { return amount; }
    public int getTenor() { return tenor; }
    public double getDp() { return dp; }
}