package com.bridgelabz.controller;

import com.bridgelabz.model.Person;
import com.bridgelabz.utility.PersonUtils;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Map<String,List<Person>> cityMap = new HashMap<>();
        Map<String,List<Person>> stateMap = new HashMap<>();

        System.out.println("Welcome to Adderess Book program");
        boolean isContinue = true;
        while (isContinue == true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1)ADD   2)EDIT   3)DELETE  4)Sort By Name  5)Sort By City  6)Sort By State  7)Sort By Zip  8)Search by City & State  9)Search by City  10)Search by State  11)Display  12)EXIT");
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
                    personList = personList.stream().sorted((p1 , p2) -> p1.getFname().compareTo(p2.getFname())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                //sort by City
                case 5:
                    personList = personList.stream().sorted((p1 , p2) -> p1.getCity().compareTo(p2.getCity())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                //sort by city
                case 6:
                    personList = personList.stream().sorted((p1 , p2) -> p1.getState().compareTo(p2.getState())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                //sort by zip
                case 7:
                    personList = personList.stream().sorted((p1 , p2) -> p1.getZip().compareTo(p2.getZip())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                //search by state and city
                case 8:
                    PersonUtils.searchByCityState(personList);
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
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                case 12:
                    isContinue = false;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }
}
