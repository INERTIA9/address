package com.bridgelabs.service;

import com.bridgelabs.model.Person;

import java.util.List;

public interface IAddressBookService {

    List<Person> findAll();
    int findByFirstNameAndLastName(Person person);
    void editPerson(Person person);
    void deletePerson(Person person);
    void addPerson(Person person);
    void writeDataToFile();
    List<Person> readDataFromFile();
    List<Person> findByCityAndState(Person person);
    List<Person> findByCity(String  city);
    List<Person> findByState(String  state);
}
