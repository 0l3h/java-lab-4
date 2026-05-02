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
        storage = loadFromJson(FILE_NAME);
        
        while (true) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Створити об’єкт");
            System.out.println("2. Вивести всі об’єкти");
            System.out.println("3. Вихід та збереження");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                createObject();
            } else if (choice.equals("2")) {
                printObjects();
            } else if (choice.equals("3")) {
                saveToJson(storage, FILE_NAME);
                System.out.println("Дані збережено.");
                break;
            }
        }
    }

    private static void createObject() {
        System.out.println("\nОберіть тип: 1.SmartPhone 2.IPhone 3.Keypad 4.SatPhone 0.Назад");
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
                    System.out.print("Супутник: "); String s = scanner.nextLine();
                    storage.add(new SatPhone(b, m, s));
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка вводу!");
        }
    }

    private static void printObjects() {
        if (storage.isEmpty()) System.out.println("Список порожній.");
        for (Phone p : storage) System.out.println(p);
    }

    // --- РОБОТА З JSON (GSON) ---

    public static void saveToJson(ArrayList<Phone> list, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();

        for (Phone p : list) {
            JsonElement element = gson.toJsonTree(p);
            // Додаємо тип класу для однозначного відновлення
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
        Gson gson = new Gson();
        File file = new File(fileName);

        if (!file.exists()) return list;

        try (FileReader reader = new FileReader(fileName)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                String type = obj.get("type").getAsString();

                switch (type) {
                    case "SmartPhone" -> list.add(gson.fromJson(obj, SmartPhone.class));
                    case "IPhone" -> list.add(gson.fromJson(obj, IPhone.class));
                    case "KeypadPhone" -> list.add(gson.fromJson(obj, KeypadPhone.class));
                    case "SatPhone" -> list.add(gson.fromJson(obj, SatPhone.class));
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка читання файлу.");
        }
        return list;
    }
}