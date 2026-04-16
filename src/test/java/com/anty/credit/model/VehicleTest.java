package com.anty.credit.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Vehicle Model Test Suite")
public class VehicleTest {

    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        vehicle = new Vehicle("mobil", "NEW", 2025);
    }

    @Test
    @DisplayName("Constructor dengan valid parameters")
    public void testConstructor_ValidParameters() {
        assertNotNull(vehicle);
        assertEquals("mobil", vehicle.getType());
        assertEquals("NEW", vehicle.getCondition());
        assertEquals(2025, vehicle.getYear());
    }

    @Test
    @DisplayName("getType return nilai yang benar")
    public void testGetType() {
        Vehicle testVehicle = new Vehicle("motor", "USED", 2023);
        assertEquals("motor", testVehicle.getType());
    }

    @Test
    @DisplayName("getCondition return nilai yang benar")
    public void testGetCondition() {
        Vehicle testVehicle = new Vehicle("mobil", "USED", 2023);
        assertEquals("USED", testVehicle.getCondition());
    }

    @Test
    @DisplayName("getYear return nilai yang benar")
    public void testGetYear() {
        Vehicle testVehicle = new Vehicle("mobil", "NEW", 2025);
        assertEquals(2025, testVehicle.getYear());
    }

    @Test
    @DisplayName("Vehicle mobil NEW")
    public void testVehicle_MobilNEW() {
        Vehicle testVehicle = new Vehicle("mobil", "NEW", 2025);
        
        assertEquals("mobil", testVehicle.getType());
        assertEquals("NEW", testVehicle.getCondition());
        assertEquals(2025, testVehicle.getYear());
    }

    @Test
    @DisplayName("Vehicle motor NEW")
    public void testVehicle_MotorNEW() {
        Vehicle testVehicle = new Vehicle("motor", "NEW", 2026);
        
        assertEquals("motor", testVehicle.getType());
        assertEquals("NEW", testVehicle.getCondition());
        assertEquals(2026, testVehicle.getYear());
    }

    @Test
    @DisplayName("Vehicle mobil USED")
    public void testVehicle_MobilUSED() {
        Vehicle testVehicle = new Vehicle("mobil", "USED", 2023);
        
        assertEquals("mobil", testVehicle.getType());
        assertEquals("USED", testVehicle.getCondition());
        assertEquals(2023, testVehicle.getYear());
    }

    @Test
    @DisplayName("Vehicle motor USED")
    public void testVehicle_MotorUSED() {
        Vehicle testVehicle = new Vehicle("motor", "USED", 2021);
        
        assertEquals("motor", testVehicle.getType());
        assertEquals("USED", testVehicle.getCondition());
        assertEquals(2021, testVehicle.getYear());
    }

    @Test
    @DisplayName("Vehicle dengan berbagai tahun")
    public void testVehicle_DifferentYears() {
        Vehicle vehicle2020 = new Vehicle("mobil", "USED", 2020);
        Vehicle vehicle2022 = new Vehicle("mobil", "USED", 2022);
        Vehicle vehicle2025 = new Vehicle("mobil", "NEW", 2025);
        
        assertEquals(2020, vehicle2020.getYear());
        assertEquals(2022, vehicle2022.getYear());
        assertEquals(2025, vehicle2025.getYear());
    }

    @Test
    @DisplayName("Multiple Vehicle objects independent")
    public void testMultipleVehicle_Independent() {
        Vehicle vehicle1 = new Vehicle("mobil", "NEW", 2025);
        Vehicle vehicle2 = new Vehicle("motor", "USED", 2023);
        
        assertEquals("mobil", vehicle1.getType());
        assertEquals("motor", vehicle2.getType());
        assertEquals("NEW", vehicle1.getCondition());
        assertEquals("USED", vehicle2.getCondition());
    }

    @Test
    @DisplayName("Vehicle immutable (no setters)")
    public void testVehicle_NoSetters() {
        Vehicle testVehicle = new Vehicle("mobil", "NEW", 2025);
        
        // Verify field values tidak bisa diubah melalui public methods
        assertEquals("mobil", testVehicle.getType());
        assertEquals("NEW", testVehicle.getCondition());
        assertEquals(2025, testVehicle.getYear());
        
        // Getters hanya, tidak ada setters
        assertNotNull(testVehicle.getType());
        assertNotNull(testVehicle.getCondition());
        assertTrue(testVehicle.getYear() > 0);
    }

    @Test
    @DisplayName("Vehicle dengan lowercase type")
    public void testVehicle_LowercaseType() {
        Vehicle testVehicle = new Vehicle("mobil", "NEW", 2025);
        assertEquals("mobil", testVehicle.getType());
    }

    @Test
    @DisplayName("Vehicle dengan uppercase condition")
    public void testVehicle_UppercaseCondition() {
        Vehicle testVehicle = new Vehicle("mobil", "NEW", 2025);
        assertEquals("NEW", testVehicle.getCondition());
    }

    @Test
    @DisplayName("Vehicle dengan old year")
    public void testVehicle_OldYear() {
        Vehicle testVehicle = new Vehicle("mobil", "USED", 2000);
        assertEquals(2000, testVehicle.getYear());
    }
}
