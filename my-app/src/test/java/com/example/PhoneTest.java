package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тести для перевірки валідації класу Phone.
 */
public class PhoneTest {

    @Test
    void testInvalidConstructor() {
        // Тестуємо створення об'єкта з некоректною ціною
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("iPhone 15", "Apple", -100, 128);
        });
    }

    @Test
    void testInvalidSetter() {
        Phone phone = new Phone("S23", "Samsung", 30000, 256);
        // Тестуємо встановлення порожньої моделі через сетер
        assertThrows(IllegalArgumentException.class, () -> {
            phone.setModel("");
        });
    }
}