package com.example;

public class Phone {
    private String brand;
    private String model;

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("Бренд: %s, Модель: %s", brand, model);
    }
}