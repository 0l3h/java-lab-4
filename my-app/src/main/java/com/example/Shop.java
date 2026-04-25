package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що демонструє агрегацію (магазин містить список телефонів).
 */
public class Shop {
    private String shopName;
    private List<Phone> inventory;

    /**
     * Конструктор магазину.
     * @param shopName назва магазину.
     */
    public Shop(String shopName) {
        if (shopName == null || shopName.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва магазину не може бути порожньою");
        }
        this.shopName = shopName;
        this.inventory = new ArrayList<>();
    }

    /**
     * Додає телефон до інвентарю магазину.
     */
    public void addPhone(Phone phone) {
        if (phone != null) {
            inventory.add(phone);
        }
    }

    public void displayInventory() {
        System.out.println("Магазин: " + shopName);
        if (inventory.isEmpty()) {
            System.out.println("Інвентар порожній.");
        } else {
            for (Phone p : inventory) {
                System.out.println(" - " + p.toString());
            }
        }
    }
}