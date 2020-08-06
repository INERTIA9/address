package com.bridgelabs.dao.impl;

import com.bridgelabs.dao.IAddressBookDAO;
import com.bridgelabs.model.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookFileOperationsImpl implements IAddressBookDAO {

    List<Person> personList = new ArrayList<>();

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public int findByFirstNameAndLastName(Person person) {
        int count = 0;
        if(personList.contains(person)){
            count = 1;
        }
        return count;
    }

    @Override
    public void editPerson(Person person) {
        String firstAndLastName = person.getFirstName() + person.getLastName();
        Iterator<Person> personIterator = personList.iterator();
        while (personIterator.hasNext()) {
            Person person1 = personIterator.next();
            if (firstAndLastName.equals(person1.getFirstName() + person1.getLastName())) {
                person1.setCity(person.getCity());
                person1.setState(person.getState());
                person1.setPhoneNumber(person.getPhoneNumber());
                person1.setZip(person.getZip());
            }
        }
    }

    @Override
    public void deletePerson(Person person) {
        String firstAndLastName = person.getFirstName() + person.getLastName();
        personList.removeIf(person1 -> firstAndLastName.equals(person1.getFirstName() + person1.getLastName()));
    }

    @Override
    public void addPerson(Person person) {
        personList.add(person);
    }

    @Override
    public List<Person> findByCityAndState(Person person) {
        return this.personList.stream().filter(p -> p.getCity().equals(person.getCity()) && p.getState().equals(person.getState())).collect(Collectors.toList());
    }

    @Override
    public List<Person> findByCity(String city) {
        return this.personList.stream().filter(p -> p.getCity().equals(city)).collect(Collectors.toList());
    }

    @Override
    public List<Person> findByState(String state) {
        return this.personList.stream().filter(p -> p.getState().equals(state)).collect(Collectors.toList());
    }
}
