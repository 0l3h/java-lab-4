package com.example;

public class KeypadPhone extends Phone {
    private boolean hasAntenna;

    public KeypadPhone(String brand, String model, boolean hasAntenna) {
        super(brand, model);
        this.hasAntenna = hasAntenna;
    }

    @Override
    public String getDescription() {
        return "Кнопковий телефон " + brand + (hasAntenna ? " з антеною" : " без антени");
    }
}