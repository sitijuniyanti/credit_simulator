package com.anty.credit.service;

import com.anty.credit.model.Loan;
import com.anty.credit.model.LoanCalculation;
import com.anty.credit.model.MobilKredit;
import com.anty.credit.model.MotorKredit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreditCalculatorService Test Suite")
public class CreditCalculatorServiceTest {

    private CreditCalculatorService calculatorService;
    private Loan loan;

    @BeforeEach
    public void setUp() {
        calculatorService = new CreditCalculatorService();
    }

    @Test
    @DisplayName("Hitung cicilan dengan tenor 1 tahun")
    public void testCalculate_Tenor1Year() {
        // Amount: 100.000, Tenor: 1 tahun, DP: 35.000, Rate: 0.08 (mobil)
        loan = new Loan(100000, 1, 35000);
        MobilKredit product = new MobilKredit();
        
        LoanCalculation result = calculatorService.calculate(loan, product);
        
        // Principal = 100000 - 35000 = 65000
        // Total = 65000 + (65000 * 0.08 * 1) = 65000 + 5200 = 70200
        // Monthly = 70200 / 12 = 5850
        double expected = 5850.0;
        assertEquals(expected, result.getMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Hitung cicilan dengan tenor 3 tahun")
    public void testCalculate_Tenor3Years() {
        // Amount: 100.000, Tenor: 3 tahun, DP: 35.000, Rate: 0.08 (mobil)
        loan = new Loan(100000, 3, 35000);
        MobilKredit product = new MobilKredit();
        
        LoanCalculation result = calculatorService.calculate(loan, product);
        
        // Principal = 100000 - 35000 = 65000
        // Total = 65000 + (65000 * 0.08 * 3) = 65000 + 15600 = 80600
        // Monthly = 80600 / 36 = 2238.89
        double expected = 80600.0 / 36;
        assertEquals(expected, result.getMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Hitung cicilan dengan tenor 6 tahun")
    public void testCalculate_Tenor6Years() {
        loan = new Loan(100000, 6, 35000);
        MobilKredit product = new MobilKredit();
        
        LoanCalculation result = calculatorService.calculate(loan, product);
        
        // Principal = 65000
        // Total = 65000 + (65000 * 0.08 * 6) = 65000 + 31200 = 96200
        // Monthly = 96200 / 72 = 1336.11
        double expected = 96200.0 / 72;
        assertEquals(expected, result.getMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Hitung cicilan dengan rate berbeda")
    public void testCalculate_DifferentRates() {
        loan = new Loan(100000, 2, 35000);
        
        // Test dengan product mobil (rate 0.08)
        MobilKredit mobilProduct = new MobilKredit();
        LoanCalculation monthly1 = calculatorService.calculate(loan, mobilProduct);
        
        // Test dengan product motor (rate 0.09)
        MotorKredit motorProduct = new MotorKredit();
        LoanCalculation monthly2 = calculatorService.calculate(loan, motorProduct);
        
        // Motor harusnya lebih tinggi dari mobil (rate lebih tinggi)
        assertTrue(monthly2.getMonthlyPayment() > monthly1.getMonthlyPayment());
    }

    @Test
    @DisplayName("Hitung cicilan dengan amount besar")
    public void testCalculate_LargeAmount() {
        // 500 juta dengan DP 35% = 175 juta
        loan = new Loan(500_000_000, 3, 175_000_000);
        MobilKredit product = new MobilKredit();
        
        LoanCalculation result = calculatorService.calculate(loan, product);
        
        // Principal = 500M - 175M = 325M
        // Total = 325M + (325M * 0.08 * 3) = 325M + 78M = 403M
        // Monthly = 403M / 36 = 11.194.444,44
        double expected = 403_000_000.0 / 36;
        assertEquals(expected, result.getMonthlyPayment(), 1);
    }

    @Test
    @DisplayName("Hitung cicilan dengan amount kecil")
    public void testCalculate_SmallAmount() {
        loan = new Loan(10000, 1, 3500);
        MobilKredit product = new MobilKredit();
        
        LoanCalculation result = calculatorService.calculate(loan, product);
        
        // Principal = 10000 - 3500 = 6500
        // Total = 6500 + (6500 * 0.08 * 1) = 6500 + 520 = 7020
        // Monthly = 7020 / 12 = 585
        double expected = 7020.0 / 12;
        assertEquals(expected, result.getMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Cicilan harus selalu positif")
    public void testCalculate_ShouldAlwaysBePositive() {
        loan = new Loan(100000, 6, 50000);
        MobilKredit product = new MobilKredit();
        
        LoanCalculation result = calculatorService.calculate(loan, product);
        
        assertTrue(result.getMonthlyPayment() > 0);
    }

    @Test
    @DisplayName("Cicilan meningkat seiring tenor meningkat dengan rate konstan")
    public void testCalculate_MonthlyIncreaseWithLongerTenor() {
        MobilKredit product = new MobilKredit();
        
        loan = new Loan(100000, 1, 35000);
        LoanCalculation res1 = calculatorService.calculate(loan, product);
        double monthly1 = res1.getMonthlyPayment();
        
        loan = new Loan(100000, 2, 35000);
        LoanCalculation res2 = calculatorService.calculate(loan, product);
        double monthly2 = res2.getMonthlyPayment();
        
        loan = new Loan(100000, 3, 35000);
        LoanCalculation res3 = calculatorService.calculate(loan, product);
        double monthly3 = res3.getMonthlyPayment();
        
        // Monthly payment harusnya naik dengan tenor yang lebih pendek
        assertTrue(monthly1 > monthly2);
        assertTrue(monthly2 > monthly3);
    }
}
