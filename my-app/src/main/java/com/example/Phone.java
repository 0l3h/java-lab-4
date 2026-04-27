package com.example;

public class Phone {
    protected String brand;
    protected String model;

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getDescription() {
        return "Це телефон " + brand;
    }
}