package com.bridgelabs.fileoperation;

import com.bridgelabs.model.Person;

import java.util.List;

public interface IFileOperations {

    void writeDataToFile(List<Person> personList);

    List<Person> readDataFromFile();
}
