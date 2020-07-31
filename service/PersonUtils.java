package com.bridgelabz.service;

import com.bridgelabz.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonUtils {

    static JSONObject personDetails = new JSONObject();
    public static Person addPersonName() {
        Person person = new Person();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First Name");
        person.setFirstName(sc.nextLine());
        personDetails.put("firstName", person.getFirstName());
        System.out.println("Enter the Last Name");
        person.setLastName(sc.nextLine());
        personDetails.put("lastName", person.getLastName());
        return person;
    }

    public static Person addPersonRecord(Person person) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the City");
            person.setCity(sc.nextLine());
            personDetails.put("city", person.getCity());
            System.out.println("Enter the State");
            person.setState(sc.nextLine());
            personDetails.put("state", person.getState());
            System.out.println("Enter the 6 Digit Zip code");
            int zip = sc.nextInt();
            int length = (int) (Math.log10(zip) + 1);
            while (length != 6) {
                System.out.println("enter 6 digit number");
                zip = sc.nextInt();
                length = (int) (Math.log10(zip) + 1);
                if (length == 6) {
                    break;
                }
            }
            person.setZip(zip);
            personDetails.put("zip", person.getZip());
            System.out.println("Enter the 10 Digit Mobile Number");
            long phoneNumber = sc.nextLong();
            length = (int) (Math.log10(phoneNumber) + 1);
            while (length != 10) {
                System.out.println("enter 10 digit number");
                phoneNumber = sc.nextLong();
                length = (int) (Math.log10(phoneNumber) + 1);
                if (length == 10) {
                    break;
                }
            }
            person.setPhoneNumber(phoneNumber);
            personDetails.put("phoneNumber", person.getPhoneNumber());
        } catch (NullPointerException | InputMismatchException e) {
            System.out.println(e);
        }
        return person;
    }

    public static void addPerson(List<Person> personList) {
        Person person = PersonUtils.addPersonName();
        String flName = person.getFirstName() + person.getLastName();
        boolean valid = PersonUtils.stringChecker(flName);
        if (valid) {
            if (personList.contains(person)) {
                System.out.println("Person Already Exist :" + person.getFirstName() + " " + person.getLastName());
            } else {
                PersonUtils.addPersonRecord(person);
                personList.add(person);
            }
        } else {
            System.out.println("Enter valid name -> Enter only character");
        }
    }

    public static void editPersonRecord(List<Person> personList) {
        Person personEdit = PersonUtils.addPersonName();
        if (personList.contains(personEdit)) {
            String firstAndLastName = personEdit.getFirstName() + personEdit.getLastName();
            Iterator<Person> personIterator = personList.iterator();
            while (personIterator.hasNext()) {
                Person person1 = personIterator.next();
                if (firstAndLastName.equals(person1.getFirstName() + person1.getLastName())) {
                    person1 = PersonUtils.addPersonRecord(person1);
                }
            }
        } else {
            System.out.println("Person Does not Exist :" + personEdit.getFirstName() + " " + personEdit.getLastName());
        }
    }

    public static void deletePersonRecord(List<Person> personList) {
        Person personDelete = PersonUtils.addPersonName();
        if (personList.contains(personDelete)) {
            String firstAndLastName = personDelete.getFirstName() + personDelete.getLastName();
            personList.removeIf(person1 -> firstAndLastName.equals(person1.getFirstName() + person1.getLastName()));
        } else {
            System.out.println("Person Does not Exist :" + personDelete.getFirstName() + " " + personDelete.getLastName());
        }
    }

    public static void searchByCityAndState(List<Person> personList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the City");
        String city = sc.nextLine();
        System.out.println("Enter the State");
        String state = sc.nextLine();
        personList.forEach(p -> {
            if (p.getCity().equals(city) && p.getState().equals(state)) {
                System.out.println(p);
            }
        });
    }

    public static void searchByCity(Map<String, List<Person>> cityMap) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the City");
        String city1 = sc.nextLine();
        if (cityMap.containsKey(city1)) {
            cityMap.get(city1).forEach(p -> {
                if (p.getCity().equals(city1)) {
                    System.out.println(p);
                }
            });
        } else {
            System.out.println("No Person found with city : " + city1);
        }
    }

    public static void searchByState(Map<String, List<Person>> stateMap) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the State");
        String state = sc.nextLine();
        if (stateMap.containsKey(state)) {
            stateMap.get(state).forEach(p -> {
                if (p.getState().equals(state)) {
                    System.out.println(p);
                }
            });
        } else {
            System.out.println("No Person found with state : " + state);
        }
    }

    public static boolean stringChecker(String checkString) {
        Pattern stringChecker = Pattern.compile("([a-zA-Z]+)");
        Matcher matchString = stringChecker.matcher(checkString);
        boolean validString = matchString.matches();
        return validString;
    }

    public static void writeToJson() {
        JSONObject personObject = new JSONObject();
        personObject.put("personDetails", personDetails);
        JSONArray personDetailsList = new JSONArray();
        personDetailsList.add(personObject);
        try (FileWriter file = new FileWriter("./ContactList.json")) {
            file.write(personDetailsList.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}