package com.bridgelabs.utility;

import java.util.Scanner;

public class UserInput {
    private final static Scanner scanner = new Scanner(System.in);

    public static int getNumber() {
        return scanner.nextInt();
    }

    public static String getString() {
        return scanner.nextLine();
    }

    public static String getFirst() {
        return scanner.next();
    }

    public static long getLong() {
        return scanner.nextLong();
    }
}
