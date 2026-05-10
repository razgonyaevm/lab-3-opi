package com.areaapp;

import java.util.ResourceBundle;

/**
 * Главный класс приложения.
 * <p>
 * Демонстрирует успешную сборку проекта с использованием Apache Ant.
 * </p>
 *
 * @author Maksim Razgonyaev
 * @version 1.0
 */
public class Main {
    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("locale.messages");
        System.out.println(bundle.getString("main.success"));
    }
}
