package com.anty.credit.factory;

public class InterestFactory {

    public static double getRate(String type) {
        if (type.equalsIgnoreCase("mobil")) return 0.08;
        if (type.equalsIgnoreCase("motor")) return 0.09;

        throw new IllegalArgumentException("Jenis kendaraan tidak valid");
    }
}