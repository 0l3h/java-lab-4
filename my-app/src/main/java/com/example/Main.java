package com.example;

import java.util.Scanner;

/**
 * Драйвер програми з консольним меню.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");
    private static final Shop myShop = new Shop("TechStore 2026");

    public static void main(String[] args) {
        printHeader();
        boolean running = true;

        while (running) {
            System.out.println("\n1. Додати телефон");
            System.out.println("2. Створити копію останнього телефону");
            System.out.println("3. Показати інвентар та лічильник");
            System.out.println("4. Вихід");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1" -> addNewPhone();
                    case "2" -> copyPhone();
                    case "3" -> {
                        myShop.displayInventory();
                        System.out.println("Всього об'єктів Phone створено: " + Phone.getObjectCount());
                    }
                    case "4" -> running = false;
                    default -> System.out.println("Невірний вибір.");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    private static void printHeader() {
        System.out.println("========================================");
        System.out.println("Програма керування магазином смартфонів");
        System.out.println("Розробник: Олег");
        System.out.println("Версія: 2.0 (Aggregation & Static)");
        System.out.println("========================================");
    }

    private static void addNewPhone() {
        System.out.print("Бренд: ");
        String brand = scanner.nextLine();
        System.out.print("Модель: ");
        String model = scanner.nextLine();
        System.out.print("Ціна: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Пам'ять: ");
        int storage = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Виберіть тип корпусу (1-PLASTIC, 2-METAL, 3-GLASS, 4-CERAMIC): ");
        int typeIdx = Integer.parseInt(scanner.nextLine());
        BodyType body = BodyType.values()[typeIdx - 1];

        Phone p = new Phone(model, brand, price, storage, body);
        myShop.addPhone(p);
    }

    private static void copyPhone() {
        System.out.println("Створюємо дублікат...");
        Phone original = new Phone("Demo", "Original", 100, 64, BodyType.PLASTIC);
        Phone copy = new Phone(original);
        myShop.addPhone(copy);
        System.out.println("Копію створено успішно.");
    }
}