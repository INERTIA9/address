package com.bridgelabz;

public class AddressBook {
    public static void main(String[] args) {
        System.out.println("Welcome to Adderess Book program");
        Person person = PersonUtils.record();
        System.out.println(person);
    }
}
