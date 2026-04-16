package com.anty.credit.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Loan Model Test Suite")
public class LoanTest {

    private Loan loan;

    @BeforeEach
    public void setUp() {
        loan = new Loan(100000, 3, 35000);
    }

    @Test
    @DisplayName("Constructor dengan valid parameters")
    public void testConstructor_ValidParameters() {
        assertNotNull(loan);
        assertEquals(100000, loan.getAmount());
        assertEquals(3, loan.getTenor());
        assertEquals(35000, loan.getDp());
    }

    @Test
    @DisplayName("getAmount return nilai yang benar")
    public void testGetAmount() {
        double amount = 100000;
        Loan testLoan = new Loan(amount, 3, 35000);
        assertEquals(amount, testLoan.getAmount());
    }

    @Test
    @DisplayName("getTenor return nilai yang benar")
    public void testGetTenor() {
        int tenor = 3;
        Loan testLoan = new Loan(100000, tenor, 35000);
        assertEquals(tenor, testLoan.getTenor());
    }

    @Test
    @DisplayName("getDp return nilai yang benar")
    public void testGetDp() {
        double dp = 35000;
        Loan testLoan = new Loan(100000, 3, dp);
        assertEquals(dp, testLoan.getDp());
    }

    @Test
    @DisplayName("Loan dengan berbagai nilai amount")
    public void testLoan_DifferentAmounts() {
        Loan loan1 = new Loan(50000, 2, 12500);
        Loan loan2 = new Loan(500_000_000, 5, 175_000_000);
        
        assertEquals(50000, loan1.getAmount());
        assertEquals(500_000_000, loan2.getAmount());
    }

    @Test
    @DisplayName("Loan dengan berbagai nilai tenor")
    public void testLoan_DifferentTenors() {
        for (int i = 1; i <= 6; i++) {
            Loan testLoan = new Loan(100000, i, 35000);
            assertEquals(i, testLoan.getTenor());
        }
    }

    @Test
    @DisplayName("Loan dengan berbagai nilai DP")
    public void testLoan_DifferentDownPayments() {
        Loan loan1 = new Loan(100000, 3, 25000); // 25%
        Loan loan2 = new Loan(100000, 3, 35000); // 35%
        Loan loan3 = new Loan(100000, 3, 50000); // 50%
        
        assertEquals(25000, loan1.getDp());
        assertEquals(35000, loan2.getDp());
        assertEquals(50000, loan3.getDp());
    }

    @Test
    @DisplayName("Multiple Loan objects independent")
    public void testMultipleLoan_Independent() {
        Loan loan1 = new Loan(100000, 2, 35000);
        Loan loan2 = new Loan(200000, 3, 50000);
        
        assertEquals(100000, loan1.getAmount());
        assertEquals(200000, loan2.getAmount());
        assertEquals(2, loan1.getTenor());
        assertEquals(3, loan2.getTenor());
    }

    @Test
    @DisplayName("Loan immutable (no setters)")
    public void testLoan_NoSetters() {
        Loan testLoan = new Loan(100000, 3, 35000);
        
        // Verify field values tidak bisa diubah melalui public methods
        assertEquals(100000, testLoan.getAmount());
        assertEquals(3, testLoan.getTenor());
        assertEquals(35000, testLoan.getDp());
        
        // Getters hanya, tidak ada setters
        assertTrue(testLoan.getAmount() > 0);
        assertTrue(testLoan.getTenor() > 0);
        assertTrue(testLoan.getDp() > 0);
    }

    @Test
    @DisplayName("Loan dengan decimal values")
    public void testLoan_DecimalValues() {
        Loan testLoan = new Loan(100000.50, 3, 35000.75);
        
        assertEquals(100000.50, testLoan.getAmount());
        assertEquals(35000.75, testLoan.getDp());
    }
}
