package com.bridgelabs.fileoperation.impl;

import com.bridgelabs.fileoperation.IFileOperations;
import com.bridgelabs.model.Person;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonFileOperationsImpl implements IFileOperations {

    final String GSON_TO_JSON_FILE_PATH = "src/main/resources/GsonAddressBook.json";

    @Override
    public void writeDataToFile(List<Person> personList) {
        String personDetail = new Gson().toJson(personList);
        try (FileWriter writer = new FileWriter(GSON_TO_JSON_FILE_PATH)) {
            writer.write(personDetail);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> readDataFromFile() {
        List<Person> personList = null;
        try {
            Person[] personDetails = new Gson().fromJson(new FileReader(GSON_TO_JSON_FILE_PATH), Person[].class);
            personList = new ArrayList<>(Arrays.asList(personDetails));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personList;
    }
}

