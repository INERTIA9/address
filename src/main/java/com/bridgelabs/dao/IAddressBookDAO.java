package com.bridgelabs.dao;

import com.bridgelabs.model.Person;

import java.util.List;

public interface IAddressBookDAO {

    List<Person> findAll();
    int findByFirstNameAndLastName(Person person);
    void editPerson(Person person);
    void deletePerson(Person person);
    void addPerson(Person person);
    List<Person> findByCityAndState(Person person);
    List<Person> findByCity(String  city);
    List<Person> findByState(String  state);

}

