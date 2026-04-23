package com.example;

import java.util.Objects;

public class Phone {
    private String brand;
    private String model;
    private int price;

    // Конструктор з параметрами
    public Phone(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    // Гетери та сетери
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    // Метод toString()
    @Override
    public String toString() {
        return "Phone{brand='" + brand + "', model='" + model + "', price=" + price + "}";
    }

    // Метод equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return price == phone.price && 
               Objects.equals(brand, phone.brand) && 
               Objects.equals(model, phone.model);
    }
}
