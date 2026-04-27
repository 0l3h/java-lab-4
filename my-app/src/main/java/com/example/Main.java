package com.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Phone> list = new ArrayList<>();
        
        // Зберігаємо різні класи в одному списку типів Phone
        list.add(new SmartPhone("Apple", "iPhone 15", "iOS"));
        list.add(new KeypadPhone("Nokia", "3310", false));

        // Поліморфний виклик: один метод getDescription() працює по-різному
        for (Phone p : list) {
            System.out.println(p.getDescription());
        }
    }
}