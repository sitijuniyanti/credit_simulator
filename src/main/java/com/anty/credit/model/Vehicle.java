package com.anty.credit.model;

public class Vehicle {
    private String type;
    private String condition;
    private int year;

    public Vehicle(String type, String condition, int year) {
        this.type = type;
        this.condition = condition;
        this.year = year;
    }

    public String getType() { return type; }
    public String getCondition() { return condition; }
    public int getYear() { return year; }
}