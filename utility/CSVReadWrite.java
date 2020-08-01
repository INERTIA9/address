package com.bridgelabz.utility;

import com.bridgelabz.model.Person;
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

public class CSVReadWrite {

    public void writeToCSV(List<Person> addressBook, String filePath) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
                    .build();
            beanToCsv.write(addressBook);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    public List<Person> readDataToList(String filePath) {
        List<Person> addressBook = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
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
