package com.bridgelabz.controller;

import com.bridgelabz.model.Person;
import com.bridgelabz.service.PersonUtils;
import com.bridgelabz.utility.JsonSimpleIO;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public static void main(String[] args) {
        JsonSimpleIO jsonSimpleIO = new JsonSimpleIO();

        final String JSON_SIMPLE_FILE_PATH = "src/main/resources/JSonSimpleAddressBook.json";
        final String OPEN_CSV_FILE_PATH = "src/main/resources/CSVAddressBook.csv";
        String filePath = "";
        final int jsonSampleOperation = 1, openCSVOperation = 2;
        int operations = 0;
        List<Person> personList = new ArrayList<>();
        Map<String, List<Person>> cityMap = new HashMap<>();
        Map<String, List<Person>> stateMap = new HashMap<>();


        boolean isContinue = true;
        System.out.println("Welcome to Address Book program");
        System.out.println("Select Below Operations:\n1. JSON SAMPLE\n2. OPEN CSV \n");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                filePath = JSON_SIMPLE_FILE_PATH;
                personList = jsonSimpleIO.getDataInList(filePath);
                operations = jsonSampleOperation;
                break;
            case 2:
                filePath = OPEN_CSV_FILE_PATH;
                operations = openCSVOperation;
                break;
        }
        while (isContinue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1)ADD   2)EDIT   3)DELETE  4)Sort By Name  5)Sort By City \n " +
                               "6)Sort By State  7)Sort By Zip  8)Search by City & State  \n" +
                               "9)Search by City  10)Search by State  11)Display  12)Create File\n" +
                              "13)EXIT");
            int select = sc.nextInt();
            switch (select) {
                //Add person details
                case 1:
                    PersonUtils.addPerson(personList);
                    cityMap = personList.stream().collect(Collectors.groupingBy(Person::getCity));
                    stateMap = personList.stream().collect(Collectors.groupingBy(Person::getState));
                    break;
                //Edit person details
                case 2:
                    PersonUtils.editPersonRecord(personList);
                    cityMap = personList.stream().collect(Collectors.groupingBy(Person::getCity));
                    stateMap = personList.stream().collect(Collectors.groupingBy(Person::getState));
                    break;
                //Delete record from Address Book
                case 3:
                    PersonUtils.deletePersonRecord(personList);
                    cityMap = personList.stream().collect(Collectors.groupingBy(Person::getCity));
                    stateMap = personList.stream().collect(Collectors.groupingBy(Person::getState));
                    break;
                //sort by Name
                case 4:
                    personList = personList.stream().sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList());
                    personList.forEach(System.out::println);
                    break;
                //sort by City
                case 5:
                    personList = personList.stream().sorted(Comparator.comparing(Person::getCity)).collect(Collectors.toList());
                    personList.forEach(System.out::println);
                    break;
                //sort by city
                case 6:
                    personList = personList.stream().sorted(Comparator.comparing(Person::getState)).collect(Collectors.toList());
                    personList.forEach(System.out::println);
                    break;
                //sort by zip
                case 7:
                    personList = personList.stream().sorted(Comparator.comparing(Person::getZip)).collect(Collectors.toList());
                    personList.forEach(System.out::println);
                    break;
                //search by state and city
                case 8:
                    PersonUtils.searchByCityAndState(personList);
                    break;
                //view by city
                case 9:
                    PersonUtils.searchByCity(cityMap);
                    break;
                //view by state
                case 10:
                    PersonUtils.searchByState(stateMap);
                    break;
                case 11:
                    personList.forEach(System.out::println);
                    break;
                case 12:
                    if(operations == 1) {
                        jsonSimpleIO.writeToJson(personList,filePath);
                    }
                    if(operations == 2){
                        PersonUtils.writeToCSV(personList,filePath);
                    }
                    break;
                case 13:
                    isContinue = false;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }
}
