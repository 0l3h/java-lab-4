package com.example;

public class KeypadPhone extends Phone {
    private boolean hasPhysicalButtons;

    public KeypadPhone(String brand, String model, boolean hasPhysicalButtons) {
        super(brand, model);
        this.hasPhysicalButtons = hasPhysicalButtons;
    }

    @Override
    public String toString() {
        return super.toString() + ", Кнопковий: " + (hasPhysicalButtons ? "Так" : "Ні");
    }
}