package com.bridgelabs.fileoperation.impl;

import com.bridgelabs.fileoperation.IFileOperations;
import com.bridgelabs.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileOperationsImpl implements IFileOperations {

    final String OPEN_CSV_FILE_PATH = "src/main/resources/CSVAddressBook.csv";

    @Override
    public void writeDataToFile(List<Person> personList) {

        try (Writer writer = Files.newBufferedWriter(Paths.get(OPEN_CSV_FILE_PATH))) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
                    .build();
            beanToCsv.write(personList);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> readDataFromFile() {
        List<Person> addressBook = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(OPEN_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader)
        ) {
            csvReader.readNext();
            String[] nextPerson;
            while ((nextPerson = csvReader.readNext()) != null) {
                addressBook.add(new Person(nextPerson[1],
                        nextPerson[2],
                        nextPerson[0],
                        nextPerson[4],
                        Integer.valueOf(nextPerson[5]),
                        Long.valueOf(nextPerson[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressBook;
    }
}
