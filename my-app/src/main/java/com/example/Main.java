package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Phone> phoneList = new ArrayList<>();

        System.out.println("Введення даних для 5 телефонів:");

        for (int i = 1; i <= 5; i++) {
            System.out.println("Телефон #" + i);
            System.out.print("Марка: ");
            String brand = scanner.nextLine();
            System.out.print("Модель: ");
            String model = scanner.nextLine();
            System.out.print("Ціна: ");
            int price = Integer.parseInt(scanner.nextLine());

            phoneList.add(new Phone(brand, model, price));
        }

        System.out.println("\nСписок всіх об'єктів:");
        for (Phone p : phoneList) {
            System.out.println(p.toString());
        }
        
        scanner.close();
    }
}
