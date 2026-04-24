package com.example;

/**
 * Клас, що представляє мобільний телефон.
 */
public class Phone {
    private String model;
    private String brand;
    private double price;
    private int storage;

    /**
     * Конструктор з валідацією параметрів.
     * @param model назва моделі (не порожня)
     * @param brand бренд (не порожній)
     * @param price ціна (більше 0)
     * @param storage обсяг пам'яті (більше 0)
     */
    public Phone(String model, String brand, double price, int storage) {
        setModel(model);
        setBrand(brand);
        setPrice(price);
        setStorage(storage);
    }

    public String getModel() { return model; }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не може бути порожньою.");
        }
        this.model = model;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Бренд не може бути порожнім.");
        }
        this.brand = brand;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0.");
        }
        this.price = price;
    }

    public int getStorage() { return storage; }

    public void setStorage(int storage) {
        if (storage <= 0) {
            throw new IllegalArgumentException("Пам'ять повинна бути більшою за 0.");
        }
        this.storage = storage;
    }

    @Override
    public String toString() {
        return String.format("Телефон [Бренд: %s, Модель: %s, Ціна: %.2f, Пам'ять: %dGB]", 
                brand, model, price, storage);
    }
}