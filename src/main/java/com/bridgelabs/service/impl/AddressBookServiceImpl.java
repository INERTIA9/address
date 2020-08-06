package com.bridgelabs.service.impl;

import com.bridgelabs.dao.IAddressBookDAO;
import com.bridgelabs.fileoperation.IFileOperations;
import com.bridgelabs.model.Person;
import com.bridgelabs.service.IAddressBookService;

import java.util.ArrayList;
import java.util.List;

public class AddressBookServiceImpl implements IAddressBookService {

    private final IAddressBookDAO iAddressBookDAO;
    private final IFileOperations iFileOperations;

    public AddressBookServiceImpl(IAddressBookDAO iAddressBookDAO,IFileOperations iFileOperations){
        this.iAddressBookDAO = iAddressBookDAO;
        this.iFileOperations = iFileOperations;
    }
    @Override
    public List<Person> findAll() {
        return this.iAddressBookDAO.findAll();
    }

    @Override
    public int findByFirstNameAndLastName(Person person) {
        return this.iAddressBookDAO.findByFirstNameAndLastName(person);
    }

    @Override
    public void editPerson(Person person) {
        this.iAddressBookDAO.editPerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        this.iAddressBookDAO.deletePerson(person);
    }

    @Override
    public void addPerson(Person person) {
        this.iAddressBookDAO.addPerson(person);
    }

    @Override
    public void writeDataToFile() {
        if(this.iFileOperations != null){
            this.iFileOperations.writeDataToFile(this.iAddressBookDAO.findAll());
        }else {
            System.out.println("Operation Not Allowed in DB Mode");
        }
    }

    @Override
    public List<Person> readDataFromFile() {
        List<Person> personList = new ArrayList<>();
        if(this.iFileOperations != null){
            personList = this.iFileOperations.readDataFromFile();
        }else {
            System.out.println("Operation Not Allowed in DB Mode");
        }
        for (Person person:personList) {
            this.addPerson(person);
        }
        return personList;
    }

    @Override
    public List<Person> findByCityAndState(Person person) {
        return this.iAddressBookDAO.findByCityAndState(person);
    }

    @Override
    public List<Person> findByCity(String city) {
        return this.iAddressBookDAO.findByCity(city);
    }

    @Override
    public List<Person> findByState(String state) {
        return this.iAddressBookDAO.findByState(state);
    }
}
