package com.example;

public class SmartPhone extends Phone {
    private String os;

    public SmartPhone(String brand, String model, String os) {
        super(brand, model);
        this.os = os;
    }

    @Override
    public String getDescription() {
        return "Смартфон " + brand + " " + model + " на базі " + os;
    }
}