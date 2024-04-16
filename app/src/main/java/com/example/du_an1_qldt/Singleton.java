package com.example.du_an1_qldt;

public class Singleton {
    private static Singleton instance;
    private int value;

    private Singleton() {
        // Private constructor to prevent instantiation from outside
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
