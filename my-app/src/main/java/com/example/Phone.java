package com.example;

/**
 * Клас, що представляє мобільний телефон.
 */
public class Phone {
    private String model;
    private String brand;
    private double price;
    private int storage;
    private BodyType bodyType;

    // Статичне поле для підрахунку об'єктів
    private static int objectCount = 0;

    /**
     * Конструктор з валідацією.
     */
    public Phone(String model, String brand, double price, int storage, BodyType bodyType) {
        validateAndSet(model, brand, price, storage, bodyType);
        objectCount++;
    }

    /**
     * Конструктор копіювання.
     * @param other об'єкт, з якого копіюються дані.
     */
    public Phone(Phone other) {
        if (other != null) {
            this.model = other.model;
            this.brand = other.brand;
            this.price = other.price;
            this.storage = other.storage;
            this.bodyType = other.bodyType;
            objectCount++;
        }
    }

    /**
     * Статичний гетер для отримання кількості створених об'єктів.
     */
    public static int getObjectCount() {
        return objectCount;
    }

    private void validateAndSet(String model, String brand, double price, int storage, BodyType bodyType) {
        if (model == null || model.trim().isEmpty()) throw new IllegalArgumentException("Модель порожня");
        if (brand == null || brand.trim().isEmpty()) throw new IllegalArgumentException("Бренд порожній");
        if (price <= 0) throw new IllegalArgumentException("Ціна <= 0");
        if (storage <= 0) throw new IllegalArgumentException("Пам'ять <= 0");
        if (bodyType == null) throw new IllegalArgumentException("Тип корпусу не вказано");

        this.model = model;
        this.brand = brand;
        this.price = price;
        this.storage = storage;
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Price: %.2f, Storage: %dGB, Body: %s)", 
                brand, model, price, storage, bodyType);
    }
}