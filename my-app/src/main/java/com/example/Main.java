package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Store myStore = new Store("Головний Склад 2026");
    private static final String FILE_NAME = "input.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadDataFromFile();
        
        while (true) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Вивести весь інвентар");
            System.out.println("2. Пошук");
            System.out.println("3. Завершити роботу");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                myStore.displayInventory();
            } else if (choice.equals("2")) {
                showSearchMenu();
            } else if (choice.equals("3")) {
                break;
            }
        }
    }

    /**
     * Зчитування даних з input.txt за форматом:
     * Тип;Бренд;Модель;ДодАтрибут;Кількість
     */
    private static void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Файл " + FILE_NAME + " не знайдено.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length < 4) continue;

                String type = p[0];
                int quantity = Integer.parseInt(p[p.length - 1]); // Останнє поле - кількість
                Phone ph = null;

                switch (type) {
                    case "SmartPhone":
                        ph = new SmartPhone(p[1], p[2], p[3]);
                        break;
                    case "IPhone":
                        ph = new IPhone(p[1], p[2]); // В IPhone бренд Apple в конструкторі
                        break;
                    case "SatPhone":
                        ph = new SatPhone(p[1], p[2], p[3]);
                        break;
                    case "KeypadPhone":
                        ph = new KeypadPhone(p[1], p[2], true);
                        break;
                }

                if (ph != null) {
                    myStore.addNewPhone(ph, quantity);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка зчитування: " + e.getMessage());
        }
    }

    private static void showSearchMenu() {
        System.out.println("1. За брендом\n2. За ОС\n3. Супутникові");
        String c = scanner.nextLine();
        ArrayList<Phone> res = new ArrayList<>();
        
        if (c.equals("1")) {
            System.out.print("Бренд: ");
            res = myStore.searchByBrand(scanner.nextLine());
        } else if (c.equals("2")) {
            System.out.print("ОС: ");
            res = myStore.searchByOs(scanner.nextLine());
        } else if (c.equals("3")) {
            res = myStore.getSatPhones();
        }

        if (res.isEmpty()) {
            System.out.println("Нічого не знайдено.");
        } else {
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i));
            }
        }
    }
}