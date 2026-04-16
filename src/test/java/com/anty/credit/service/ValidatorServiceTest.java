package com.anty.credit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidatorService Test Suite")
public class ValidatorServiceTest {

    @BeforeEach
    public void setUp() {
        // Setup jika diperlukan
    }

    // ===== isValidType Tests =====
    @Test
    @DisplayName("Validasi tipe 'mobil' valid")
    public void testIsValidType_Mobil_Valid() {
        assertTrue(ValidatorService.isValidType("mobil"));
        assertTrue(ValidatorService.isValidType("MOBIL"));
        assertTrue(ValidatorService.isValidType("Mobil"));
    }

    @Test
    @DisplayName("Validasi tipe 'motor' valid")
    public void testIsValidType_Motor_Valid() {
        assertTrue(ValidatorService.isValidType("motor"));
        assertTrue(ValidatorService.isValidType("MOTOR"));
        assertTrue(ValidatorService.isValidType("Motor"));
    }

    @Test
    @DisplayName("Validasi tipe dengan angka invalid")
    public void testIsValidType_WithNumbers_Invalid() {
        assertFalse(ValidatorService.isValidType("mobil123"));
        assertFalse(ValidatorService.isValidType("123motor"));
    }

    @Test
    @DisplayName("Validasi tipe invalid")
    public void testIsValidType_Invalid() {
        assertFalse(ValidatorService.isValidType("truck"));
        assertFalse(ValidatorService.isValidType("bus"));
        assertFalse(ValidatorService.isValidType(""));
    }

    // ===== isValidCondition Tests =====
    @Test
    @DisplayName("Validasi kondisi 'NEW' valid")
    public void testIsValidCondition_NEW_Valid() {
        assertTrue(ValidatorService.isValidCondition("NEW"));
        assertTrue(ValidatorService.isValidCondition("new"));
        assertTrue(ValidatorService.isValidCondition("New"));
    }

    @Test
    @DisplayName("Validasi kondisi 'USED' valid")
    public void testIsValidCondition_USED_Valid() {
        assertTrue(ValidatorService.isValidCondition("USED"));
        assertTrue(ValidatorService.isValidCondition("used"));
        assertTrue(ValidatorService.isValidCondition("Used"));
    }

    @Test
    @DisplayName("Validasi kondisi invalid")
    public void testIsValidCondition_Invalid() {
        assertFalse(ValidatorService.isValidCondition("REFURBISHED"));
        assertFalse(ValidatorService.isValidCondition("OLD"));
        assertFalse(ValidatorService.isValidCondition(""));
    }

    // ===== isValidYear Tests =====
    @Test
    @DisplayName("Validasi tahun NEW valid")
    public void testIsValidYear_NEW_Valid() {
        // 2025 dan 2026 valid untuk NEW tahun 2026
        assertTrue(ValidatorService.isValidYear(2025, "NEW"));
        assertTrue(ValidatorService.isValidYear(2026, "NEW"));
    }

    @Test
    @DisplayName("Validasi tahun NEW di bawah range invalid")
    public void testIsValidYear_NEW_BelowRange_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.isValidYear(2024, "NEW"),
            "Tahun 2024 tidak valid untuk NEW (harus minimal 2025)");
    }

    @Test
    @DisplayName("Validasi tahun NEW di atas range invalid")
    public void testIsValidYear_NEW_AboveRange_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.isValidYear(2027, "NEW"),
            "Tahun 2027 tidak valid untuk NEW (max tahun sekarang)");
    }

    @Test
    @DisplayName("Validasi tahun USED valid")
    public void testIsValidYear_USED_Valid() {
        // 2021-2026 valid untuk USED tahun 2026
        assertTrue(ValidatorService.isValidYear(2021, "USED"));
        assertTrue(ValidatorService.isValidYear(2023, "USED"));
        assertTrue(ValidatorService.isValidYear(2026, "USED"));
    }

    @Test
    @DisplayName("Validasi tahun USED di bawah range invalid")
    public void testIsValidYear_USED_BelowRange_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.isValidYear(2020, "USED"),
            "Tahun 2020 tidak valid untuk USED (harus minimal 2021)");
    }

    @Test
    @DisplayName("Validasi tahun USED 3 digit invalid")
    public void testIsValidYear_ThreeDigit_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.isValidYear(999, "USED"),
            "Tahun harus 4 digit");
    }

    @Test
    @DisplayName("Validasi tahun USED 5 digit invalid")
    public void testIsValidYear_FiveDigit_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.isValidYear(10000, "USED"),
            "Tahun harus 4 digit");
    }

    // ===== validateType Tests =====
    @Test
    @DisplayName("validateType dengan tipe valid")
    public void testValidateType_Valid() {
        // Tidak throw exception
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 3, 35000));
    }

    @Test
    @DisplayName("validateType dengan tipe invalid throw exception")
    public void testValidateType_Invalid_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("truck", "NEW", 2025, 100000, 3, 35000),
            "Harus throw exception untuk tipe yang tidak valid");
    }

    // ===== validateCondition Tests =====
    @Test
    @DisplayName("validateCondition dengan kondisi valid")
    public void testValidateCondition_Valid() {
        // Tidak throw exception
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 3, 35000));
    }

    @Test
    @DisplayName("validateCondition dengan kondisi invalid throw exception")
    public void testValidateCondition_Invalid_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "OLD", 2025, 100000, 3, 35000),
            "Harus throw exception untuk kondisi yang tidak valid");
    }

    // ===== validateTenor Tests =====
    @Test
    @DisplayName("validateTenor dengan tenor valid")
    public void testValidateTenor_Valid() {
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 6, 35000));
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 1, 35000));
    }

    @Test
    @DisplayName("validateTenor dengan tenor kurang dari 1 invalid")
    public void testValidateTenor_BelowOne_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 0, 35000),
            "Tenor harus minimal 1 tahun");
    }

    @Test
    @DisplayName("validateTenor dengan tenor lebih dari 6 invalid")
    public void testValidateTenor_AboveSix_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 7, 35000),
            "Tenor harus maksimal 6 tahun");
    }

    // ===== validateAmount Tests =====
    @Test
    @DisplayName("validateAmount dengan amount valid")
    public void testValidateAmount_Valid() {
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 3, 35000));
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "NEW", 2025, 1_000_000_000, 3, 350_000_000));
    }

    @Test
    @DisplayName("validateAmount dengan amount 0 invalid")
    public void testValidateAmount_Zero_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, 0, 3, 35000),
            "Amount harus lebih dari 0");
    }

    @Test
    @DisplayName("validateAmount dengan amount negatif invalid")
    public void testValidateAmount_Negative_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, -100000, 3, 35000),
            "Amount tidak boleh negatif");
    }

    @Test
    @DisplayName("validateAmount dengan amount lebih dari 1 miliar invalid")
    public void testValidateAmount_OverBillion_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, 1_000_000_001, 3, 35000),
            "Amount tidak boleh lebih dari 1 miliar");
    }

    // ===== validateDP Tests =====
    @Test
    @DisplayName("validateDP NEW dengan DP 35% valid")
    public void testValidateDP_NEW_35Percent_Valid() {
        double amount = 100000;
        double dpAmount = amount * 0.35;
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "NEW", 2025, amount, 3, dpAmount));
    }

    @Test
    @DisplayName("validateDP NEW dengan DP kurang 35% invalid")
    public void testValidateDP_NEW_Below35_Invalid() {
        double amount = 100000;
        double dpAmount = amount * 0.34;
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, amount, 3, dpAmount),
            "DP NEW minimal 35%");
    }

    @Test
    @DisplayName("validateDP USED dengan DP 25% valid")
    public void testValidateDP_USED_25Percent_Valid() {
        double amount = 100000;
        double dpAmount = amount * 0.25;
        assertDoesNotThrow(() -> ValidatorService.validate("mobil", "USED", 2023, amount, 3, dpAmount));
    }

    @Test
    @DisplayName("validateDP USED dengan DP kurang 25% invalid")
    public void testValidateDP_USED_Below25_Invalid() {
        double amount = 100000;
        double dpAmount = amount * 0.24;
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "USED", 2023, amount, 3, dpAmount),
            "DP USED minimal 25%");
    }

    @Test
    @DisplayName("validateDP dengan DP 0 invalid")
    public void testValidateDP_Zero_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 3, 0),
            "DP harus lebih dari 0");
    }

    @Test
    @DisplayName("validateDP dengan DP >= amount invalid")
    public void testValidateDP_GreaterOrEqual_Amount_Invalid() {
        assertThrows(IllegalArgumentException.class, 
            () -> ValidatorService.validate("mobil", "NEW", 2025, 100000, 3, 100000),
            "DP tidak boleh >= amount");
    }

    // ===== CreditData Test =====
    @Test
    @DisplayName("CreditData constructor dan getters")
    public void testCreditData_ConstructorAndGetters() {
        ValidatorService.CreditData data = new ValidatorService.CreditData(
            "mobil", "NEW", 2025, 100000, 3, 35000
        );
        
        assertEquals("mobil", data.type);
        assertEquals("NEW", data.condition);
        assertEquals(2025, data.year);
        assertEquals(100000, data.amount);
        assertEquals(3, data.tenor);
        assertEquals(35000, data.dp);
    }
}
