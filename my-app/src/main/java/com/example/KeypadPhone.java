package com.example;

public class KeypadPhone extends Phone {
    private boolean hasButtons;

    public KeypadPhone(String brand, String model, boolean hasButtons) {
        super(brand, model);
        this.hasButtons = hasButtons;
    }

    public boolean isHasButtons() { return hasButtons; }

    @Override
    public String toString() {
        return super.toString() + ", Кнопковий: " + (hasButtons ? "Так" : "Ні");
    }
}