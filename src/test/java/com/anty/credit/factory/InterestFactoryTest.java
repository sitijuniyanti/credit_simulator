package com.anty.credit.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InterestFactory Test Suite")
public class InterestFactoryTest {

    @Test
    @DisplayName("Bunga untuk mobil adalah 0.08")
    public void testGetRate_Mobil() {
        double rate = InterestFactory.getRate("mobil");
        assertEquals(0.08, rate);
    }

    @Test
    @DisplayName("Bunga untuk mobil case-insensitive")
    public void testGetRate_Mobil_CaseInsensitive() {
        assertEquals(0.08, InterestFactory.getRate("MOBIL"));
        assertEquals(0.08, InterestFactory.getRate("Mobil"));
        assertEquals(0.08, InterestFactory.getRate("MoBiL"));
    }

    @Test
    @DisplayName("Bunga untuk motor adalah 0.09")
    public void testGetRate_Motor() {
        double rate = InterestFactory.getRate("motor");
        assertEquals(0.09, rate);
    }

    @Test
    @DisplayName("Bunga untuk motor case-insensitive")
    public void testGetRate_Motor_CaseInsensitive() {
        assertEquals(0.09, InterestFactory.getRate("MOTOR"));
        assertEquals(0.09, InterestFactory.getRate("Motor"));
        assertEquals(0.09, InterestFactory.getRate("MoToR"));
    }

    @Test
    @DisplayName("Bunga motor lebih tinggi dari mobil")
    public void testGetRate_Motor_HigherThanMobil() {
        double mobilRate = InterestFactory.getRate("mobil");
        double motorRate = InterestFactory.getRate("motor");
        assertTrue(motorRate > mobilRate);
    }

    @Test
    @DisplayName("Tipe kendaraan invalid throw exception")
    public void testGetRate_InvalidType_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> InterestFactory.getRate("truck"),
            "Harus throw exception untuk tipe yang tidak valid");
    }

    @Test
    @DisplayName("Tipe kosong throw exception")
    public void testGetRate_EmptyString_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> InterestFactory.getRate(""),
            "Harus throw exception untuk string kosong");
    }

    @Test
    @DisplayName("Tipe null throw exception")
    public void testGetRate_Null_ThrowsException() {
        assertThrows(Exception.class, 
            () -> InterestFactory.getRate(null),
            "Harus throw exception untuk null");
    }

    @Test
    @DisplayName("Bunga selalu positif")
    public void testGetRate_ShouldAlwaysBePositive() {
        double mobilRate = InterestFactory.getRate("mobil");
        double motorRate = InterestFactory.getRate("motor");
        
        assertTrue(mobilRate > 0);
        assertTrue(motorRate > 0);
    }

    @Test
    @DisplayName("Rate mobil dan motor dalam range 0.01-0.15")
    public void testGetRate_InValidRange() {
        double mobilRate = InterestFactory.getRate("mobil");
        double motorRate = InterestFactory.getRate("motor");
        
        assertTrue(mobilRate >= 0.01 && mobilRate <= 0.15);
        assertTrue(motorRate >= 0.01 && motorRate <= 0.15);
    }
}
