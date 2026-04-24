package com.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Основний клас для керування списком телефонів через консольне меню.
 */
public class PhoneManager {
    private static final List<Phone> phones = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Створити новий телефон");
            System.out.println("2. Вивести всі телефони");
            System.out.println("3. Завершити роботу");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> createPhone();
                case "2" -> showPhones();
                case "3" -> running = false;
                default -> System.out.println("Некоректний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void createPhone() {
        try {
            System.out.print("Введіть бренд: ");
            String brand = readString();
            System.out.print("Введіть модель: ");
            String model = readString();
            System.out.print("Введіть ціну: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Введіть обсяг пам'яті (GB): ");
            int storage = Integer.parseInt(scanner.nextLine());

            phones.add(new Phone(model, brand, price, storage));
            System.out.println("Телефон успішно додано!");
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введено нечислове значення для ціни або пам'яті.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static String readString() {
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) throw new IllegalArgumentException("Рядок не може бути порожнім.");
        return input;
    }

    private static void showPhones() {
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            phones.forEach(System.out::println);
        }
    }
}