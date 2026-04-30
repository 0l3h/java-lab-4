package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Phone> storage = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу");
            System.out.print("Виберіть дію: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> objectCreationMenu();
                case "2" -> printAllObjects();
                case "3" -> {
                    System.out.println("Програму завершено.");
                    return;
                }
                default -> System.out.println("Некоректний ввід.");
            }
        }
    }

    private static void objectCreationMenu() {
        System.out.println("\nОберіть тип об'єкта:");
        System.out.println("1. SmartPhone");
        System.out.println("2. KeypadPhone");
        System.out.println("3. IPhone");
        System.out.println("4. SatPhone");
        System.out.println("0. Повернутися назад");
        System.out.print("Ваш вибір: ");

        String type = scanner.nextLine();
        try {
            switch (type) {
                case "1" -> {
                    System.out.print("Бренд: "); String b = scanner.nextLine();
                    System.out.print("Модель: "); String m = scanner.nextLine();
                    System.out.print("ОС: "); String os = scanner.nextLine();
                    storage.add(new SmartPhone(b, m, os));
                }
                case "2" -> {
                    System.out.print("Бренд: "); String b = scanner.nextLine();
                    System.out.print("Модель: "); String m = scanner.nextLine();
                    storage.add(new KeypadPhone(b, m, true));
                }
                case "3" -> {
                    System.out.print("Модель (напр. 15 Pro): "); String m = scanner.nextLine();
                    System.out.print("Версія FaceID: "); String f = scanner.nextLine();
                    storage.add(new IPhone(m, f));
                }
                case "4" -> {
                    System.out.print("Бренд: "); String b = scanner.nextLine();
                    System.out.print("Модель: "); String m = scanner.nextLine();
                    System.out.print("Супутникова мережа (Iridium/Thuraya): "); String s = scanner.nextLine();
                    storage.add(new SatPhone(b, m, s));
                }
                case "0" -> { return; }
                default -> System.out.println("Тип не знайдено.");
            }
        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    private static void printAllObjects() {
        if (storage.isEmpty()) {
            System.out.println("Колекція порожня.");
        } else {
            System.out.println("\n--- СПИСОК ОБ'ЄКТІВ ---");
            for (int i = 0; i < storage.size(); i++) {
                System.out.println((i + 1) + ". " + storage.get(i).toString());
            }
        }
    }
}