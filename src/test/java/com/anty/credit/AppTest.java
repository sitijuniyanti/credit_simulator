package com.anty.credit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import com.anty.credit.util.FormatHelper;

@DisplayName("App Integration Test Suite")
public class AppTest {

    @BeforeEach
    public void setUp() {
        // Setup jika diperlukan
    }

    @Test
    @DisplayName("App dapat diinstansiasi")
    public void testAppCanBeInstantiated() {
        // App adalah main class, verify bisa diakses
        assertNotNull(App.class);
    }

    @Test
    @DisplayName("Main class exists dan accessible")
    public void testMainClassExists() {
        try {
            Class<?> appClass = Class.forName("com.anty.credit.App");
            assertNotNull(appClass);
        } catch (ClassNotFoundException e) {
            fail("App class should exist");
        }
    }

    @Test
    @DisplayName("App memiliki method showMenu")
    public void testShowMenuMethodExists() {
        try {
            App.class.getMethod("showMenu");
        } catch (NoSuchMethodException e) {
            fail("showMenu method should exist");
        }
    }

    @Test
    @DisplayName("App memiliki method runSimulation")
    public void testRunSimulationMethodExists() {
        try {
            App.class.getMethod("runSimulation", java.util.Scanner.class);
        } catch (NoSuchMethodException e) {
            fail("runSimulation method should exist");
        }
    }

    @Test
    @DisplayName("App memiliki method runCalculation")
    public void testRunCalculationMethodExists() {
        try {
            App.class.getMethod("runCalculation", 
                String.class, String.class, int.class, double.class, int.class, double.class);
        } catch (NoSuchMethodException e) {
            fail("runCalculation method should exist");
        }
    }

    @Test
    @DisplayName("FormatHelper memiliki method formatRupiah")
    public void testFormatRupiahMethodExists() {
        try {
            FormatHelper.class.getMethod("formatRupiah", double.class);
        } catch (NoSuchMethodException e) {
            fail("formatRupiah method should exist in FormatHelper");
        }
    }

    @Test
    @DisplayName("FormatHelper memiliki method formatPercent")
    public void testFormatPercentMethodExists() {
        try {
            FormatHelper.class.getMethod("formatPercent", double.class);
        } catch (NoSuchMethodException e) {
            fail("formatPercent method should exist in FormatHelper");
        }
    }
}

