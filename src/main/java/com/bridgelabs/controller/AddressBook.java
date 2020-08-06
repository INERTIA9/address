package com.bridgelabs.controller;

import com.bridgelabs.dao.impl.AddressBookDAOImplDB;
import com.bridgelabs.dao.impl.AddressBookFileOperationsImpl;
import com.bridgelabs.fileoperation.impl.CSVFileOperationsImpl;
import com.bridgelabs.fileoperation.impl.GsonFileOperationsImpl;
import com.bridgelabs.fileoperation.impl.JsonFileOperationsImpl;
import com.bridgelabs.model.Person;
import com.bridgelabs.service.IAddressBookService;
import com.bridgelabs.utility.PersonInputOutput;
import com.bridgelabs.service.impl.AddressBookServiceImpl;
import com.bridgelabs.utility.*;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {

    public static void main(String[] args) {

        IAddressBookService iAddressBookService = new AddressBookServiceImpl(new AddressBookDAOImplDB(), null);
        ;
        boolean isContinue = true;
        System.out.println("Welcome to Address Book program");
        System.out.println("Select Below Operations:\n1. JSON SAMPLE\n2. OPEN CSV \n3.JSONFileUsingGson \n4.DB Operation");
        int option = UserInput.getNumber();
        switch (option) {
            case 1:
                iAddressBookService = new AddressBookServiceImpl(new AddressBookFileOperationsImpl(), new JsonFileOperationsImpl());
                iAddressBookService.readDataFromFile();
                break;
            case 2:
                iAddressBookService = new AddressBookServiceImpl(new AddressBookFileOperationsImpl(), new CSVFileOperationsImpl());
                iAddressBookService.readDataFromFile();
                break;
            case 3:
                iAddressBookService = new AddressBookServiceImpl(new AddressBookFileOperationsImpl(), new GsonFileOperationsImpl());
                iAddressBookService.readDataFromFile();
                break;
            case 4:
                iAddressBookService = new AddressBookServiceImpl(new AddressBookDAOImplDB(), null);
                break;
            default:
                System.out.println("invalid input");
                break;
        }
        while (isContinue) {
            System.out.println("1)ADD   \n2)EDIT   \n3)DELETE  \n4)Sort By Name  \n5)Sort By City \n" +
                    "6)Sort By State  \n7)Sort By Zip  \n8)Search by City & State  \n" +
                    "9)Search by City  \n10)Search by State  \n11)Display  \n12)Save File\n" +
                    "13)EXIT");
            int select = UserInput.getNumber();
            switch (select) {
                case 1:
                    Person personAdd = PersonInputOutput.getPersonName();
                    if (iAddressBookService.findByFirstNameAndLastName(personAdd) != 0) {
                        System.out.println("Person Already Exist");
                    } else {
                        personAdd = PersonInputOutput.addPersonRecord(personAdd);
                        iAddressBookService.addPerson(personAdd);
                    }
                    break;
                case 2:
                    Person personEdit = PersonInputOutput.getPersonName();
                    if (iAddressBookService.findByFirstNameAndLastName(personEdit) == 0) {
                        System.out.println("No Data Found");
                    } else {
                        personEdit = PersonInputOutput.addPersonRecord(personEdit);
                        iAddressBookService.editPerson(personEdit);

                    }
                    break;
                case 3:
                    Person personDelete = PersonInputOutput.getPersonName();
                    if (iAddressBookService.findByFirstNameAndLastName(personDelete) == 0) {
                        System.out.println("No Data Found");
                    } else {
                        iAddressBookService.deletePerson(personDelete);

                    }
                    break;
                case 4:
                    PersonInputOutput.display(iAddressBookService.findAll().stream().sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList()));
                    break;
                case 5:
                    PersonInputOutput.display(iAddressBookService.findAll().stream().sorted(Comparator.comparing(Person::getCity)).collect(Collectors.toList()));
                    break;
                case 6:
                    PersonInputOutput.display(iAddressBookService.findAll().stream().sorted(Comparator.comparing(Person::getState)).collect(Collectors.toList()));
                    break;
                case 7:
                    PersonInputOutput.display(iAddressBookService.findAll().stream().sorted(Comparator.comparing(Person::getZip)).collect(Collectors.toList()));
                    break;
                case 8:
                    Person person = PersonInputOutput.getCityAndState();
                    PersonInputOutput.display(iAddressBookService.findByCityAndState(person));
                    break;
                case 9:
                    String city = PersonInputOutput.getCity();
                    PersonInputOutput.display(iAddressBookService.findByCity(city));
                    break;
                case 10:
                    String state = PersonInputOutput.getState();
                    PersonInputOutput.display(iAddressBookService.findByState(state));
                    break;
                case 11:
                    PersonInputOutput.display(iAddressBookService.findAll());
                    break;
                case 12:
                    iAddressBookService.writeDataToFile();
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
