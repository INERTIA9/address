package com.bridgelabs.utility;

import com.bridgelabs.model.Person;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonInputOutput {

    public static Person getPersonName() {
        Person person = new Person();
        String firstAndLastName;
        do {
            System.out.println("Enter the First Name");
            person.setFirstName(UserInput.getFirst());
            System.out.println("Enter the Last Name");
            person.setLastName(UserInput.getFirst());
            firstAndLastName = person.getFirstName() + person.getLastName();
        } while (!PersonInputOutput.stringChecker(firstAndLastName));
        return person;
    }

    public static Person addPersonRecord(Person person) {
        try {
            System.out.println("Enter the City");
            person.setCity(UserInput.getFirst());
            System.out.println("Enter the State");
            person.setState(UserInput.getFirst());
            System.out.println("Enter the 6 Digit Zip code");
            int zip = UserInput.getNumber();
            int length = (int) (Math.log10(zip) + 1);
            while (length != 6) {
                System.out.println("enter 6 digit number");
                zip = UserInput.getNumber();
                length = (int) (Math.log10(zip) + 1);
                if (length == 6) {
                    break;
                }
            }
            person.setZip(zip);
            System.out.println("Enter the 10 Digit Mobile Number");
            long phoneNumber = UserInput.getLong();
            length = (int) (Math.log10(phoneNumber) + 1);
            while (length != 10) {
                System.out.println("enter 10 digit number");
                phoneNumber = UserInput.getLong();
                length = (int) (Math.log10(phoneNumber) + 1);
                if (length == 10) {
                    break;
                }
            }
            person.setPhoneNumber(phoneNumber);
        } catch (NullPointerException | InputMismatchException e) {
            System.out.println(e);
        }
        return person;
    }

    public static Person getCityAndState() {
        Person person = new Person();
        System.out.println("Enter the City");
        String city = UserInput.getString();
        person.setCity(city);
        System.out.println("Enter the State");
        String state = UserInput.getString();
        person.setState(state);
        return person;
    }

    public static String getCity() {
        System.out.println("Enter the City");
        return UserInput.getString();
    }

    public static String getState() {
        System.out.println("Enter the State");
        return UserInput.getString();
    }

    public static boolean stringChecker(String checkString) {
        Pattern stringChecker = Pattern.compile("([a-zA-Z]+)");
        Matcher matchString = stringChecker.matcher(checkString);
        return matchString.matches();
    }

    public static void display(List<Person> personList) {
        personList.forEach(System.out::println);
    }
}