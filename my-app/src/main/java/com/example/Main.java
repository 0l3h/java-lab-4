package com.example;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Phone> storage = new ArrayList<>();
    private static final String FILE_NAME = "input.json";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Завантаження даних при старті
        storage = loadFromJson(FILE_NAME);
        
        while (true) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Пошук об’єкта");
            System.out.println("4. Завершити роботу");
            System.out.print("Виберіть дію: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> objectCreationMenu();
                case "2" -> printAllObjects();
                case "3" -> searchMenu();
                case "4" -> {
                    saveToJson(storage, FILE_NAME);
                    System.out.println("Дані збережено. Програму завершено.");
                    return;
                }
                default -> System.out.println("Некоректний ввід.");
            }
        }
    }

    // --- МЕНЮ СТВОРЕННЯ ---
    private static void objectCreationMenu() {
        System.out.println("\nОберіть тип: 1.SmartPhone 2.IPhone 3.KeypadPhone 4.SatPhone 0.Назад");
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
                    System.out.print("Модель: "); String m = scanner.nextLine();
                    System.out.print("FaceID версія: "); String f = scanner.nextLine();
                    storage.add(new IPhone(m, f));
                }
                case "3" -> {
                    System.out.print("Бренд: "); String b = scanner.nextLine();
                    System.out.print("Модель: "); String m = scanner.nextLine();
                    storage.add(new KeypadPhone(b, m, true));
                }
                case "4" -> {
                    System.out.print("Бренд: "); String b = scanner.nextLine();
                    System.out.print("Модель: "); String m = scanner.nextLine();
                    System.out.print("Мережа: "); String s = scanner.nextLine();
                    storage.add(new SatPhone(b, m, s));
                }
                case "0" -> { return; }
            }
        } catch (Exception e) {
            System.out.println("Помилка при створенні об'єкта.");
        }
    }

    private static void printAllObjects() {
        if (storage.isEmpty()) {
            System.out.println("Колекція порожня.");
        } else {
            for (Phone p : storage) System.out.println(p);
        }
    }

    // --- МЕНЮ ПОШУКУ ---
    private static void searchMenu() {
        if (storage.isEmpty()) {
            System.out.println("Колекція порожня!");
            return;
        }
        System.out.println("\n--- ПОШУК ---");
        System.out.println("1. За брендом");
        System.out.println("2. За операційною системою (для смартфонів)");
        System.out.println("3. Тільки супутникові телефони");
        System.out.println("0. Назад");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> searchByBrand();
            case "2" -> searchByOs();
            case "3" -> searchSatPhones();
            case "0" -> { return; }
        }
    }

    private static void searchByBrand() {
        System.out.print("Введіть бренд: ");
        String brand = scanner.nextLine();
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone p : storage) {
            if (p.getBrand().equalsIgnoreCase(brand)) result.add(p);
        }
        displayResults(result);
    }

    private static void searchByOs() {
        System.out.print("Введіть ОС: ");
        String os = scanner.nextLine();
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone p : storage) {
            if (p instanceof SmartPhone && ((SmartPhone) p).getOs().equalsIgnoreCase(os)) {
                result.add(p);
            }
        }
        displayResults(result);
    }

    private static void searchSatPhones() {
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone p : storage) {
            if (p instanceof SatPhone) result.add(p);
        }
        displayResults(result);
    }

    private static void displayResults(ArrayList<Phone> res) {
        if (res.isEmpty()) System.out.println("Нічого не знайдено.");
        else for (Phone p : res) System.out.println(p);
    }

    // --- JSON ТА GSON ---
    public static void saveToJson(ArrayList<Phone> list, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();
        for (Phone p : list) {
            JsonElement element = gson.toJsonTree(p);
            element.getAsJsonObject().addProperty("type", p.getClass().getSimpleName());
            jsonArray.add(element);
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(jsonArray, writer);
        } catch (IOException e) {
            System.out.println("Помилка запису!");
        }
    }

    public static ArrayList<Phone> loadFromJson(String fileName) {
        ArrayList<Phone> list = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return list;

        try (FileReader reader = new FileReader(fileName)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            Gson gson = new Gson();
            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                String type = obj.get("type").getAsString();
                switch (type) {
                    case "SmartPhone" -> list.add(gson.fromJson(obj, SmartPhone.class));
                    case "IPhone" -> list.add(gson.fromJson(obj, IPhone.class));
                    case "KeypadPhone" -> list.add(gson.fromJson(obj, KeypadPhone.class));
                    case "SatPhone" -> list.add(gson.fromJson(obj, SatPhone.class));
                    case "Phone" -> list.add(gson.fromJson(obj, Phone.class));
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка завантаження. Файл буде перезаписаний.");
        }
        return list;
    }
}