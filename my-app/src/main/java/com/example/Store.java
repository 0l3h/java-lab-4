package com.example;

import java.util.ArrayList;

/**
 * Клас-контейнер для зберігання телефонів та їх кількості.
 */
public class Store {
    private String storeName;
    private ArrayList<InventoryItem> items;

    /**
     * Допоміжний клас для зв'язку об'єкта з його кількістю.
     */
    private class InventoryItem {
        Phone phone;
        int quantity;

        InventoryItem(Phone phone, int quantity) {
            this.phone = phone;
            this.quantity = quantity;
        }
    }

    public Store(String storeName) {
        this.storeName = storeName;
        this.items = new ArrayList<>();
    }

    /**
     * Додає телефон до магазину. Якщо телефон вже є, збільшує його кількість.
     */
    public void addNewPhone(Phone ph, int quantity) {
        if (ph == null || quantity < 0) return;

        boolean found = false;
        for (int i = 0; i < items.size(); i++) {
            InventoryItem item = items.get(i);
            // Перевірка на ідентичність об'єкта
            if (item.phone.getBrand().equals(ph.getBrand()) && 
                item.phone.getModel().equals(ph.getModel())) {
                item.quantity += quantity;
                found = true;
                break;
            }
        }

        if (!found) {
            items.add(new InventoryItem(ph, quantity));
        }
    }

    // --- МЕТОДИ ПОШУКУ ---

    public ArrayList<Phone> searchByBrand(String brand) {
        ArrayList<Phone> results = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Phone p = items.get(i).phone;
            if (p.getBrand().equalsIgnoreCase(brand)) {
                results.add(p);
            }
        }
        return results;
    }

    public ArrayList<Phone> searchByOs(String os) {
        ArrayList<Phone> results = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Phone p = items.get(i).phone;
            if (p instanceof SmartPhone) {
                SmartPhone sp = (SmartPhone) p;
                if (sp.getOs().equalsIgnoreCase(os)) {
                    results.add(p);
                }
            }
        }
        return results;
    }

    public ArrayList<Phone> getSatPhones() {
        ArrayList<Phone> results = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Phone p = items.get(i).phone;
            if (p instanceof SatPhone) {
                results.add(p);
            }
        }
        return results;
    }

    public void displayInventory() {
        System.out.println("\n--- ІНВЕНТАР МАГАЗИНУ: " + storeName + " ---");
        if (items.isEmpty()) {
            System.out.println("Склад порожній.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                InventoryItem item = items.get(i);
                System.out.println(item.phone.toString() + " | Кількість: " + item.quantity);
            }
        }
    }
}