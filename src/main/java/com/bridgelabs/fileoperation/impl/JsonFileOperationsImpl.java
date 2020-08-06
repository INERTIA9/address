package com.bridgelabs.fileoperation.impl;

import com.bridgelabs.fileoperation.IFileOperations;
import com.bridgelabs.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileOperationsImpl implements IFileOperations {

    final String JSON_SIMPLE_FILE_PATH = "src/main/resources/JSonSimpleAddressBook.json";

    @Override
    public void writeDataToFile(List<Person> personList) {
        JSONArray jsonArray = new JSONArray();
        personList.forEach(person -> {
            JSONObject personDetails = new JSONObject();
            personDetails.put("First Name", person.getFirstName());
            personDetails.put("Last Name", person.getLastName());
            personDetails.put("Phone", person.getPhoneNumber());
            personDetails.put("City", person.getCity());
            personDetails.put("State", person.getState());
            personDetails.put("Zip", person.getZip());
            JSONObject personObject = new JSONObject();
            personObject.put("person", personDetails);
            jsonArray.add(personObject);
        });
        try (FileWriter file = new FileWriter(JSON_SIMPLE_FILE_PATH)) {
            file.write(jsonArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> readDataFromFile() {
        List<Person> addressBookList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(JSON_SIMPLE_FILE_PATH);
            Object obj = jsonParser.parse(fileReader);
            JSONArray personList = (JSONArray) obj;
            for (Object person : personList) {
                JSONObject jsonObject = (JSONObject) person;
                Person person1 = parseJSONObject(jsonObject);
                addressBookList.add(person1);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return addressBookList;
    }

    private Person parseJSONObject(JSONObject personJson) {
        JSONObject personObj = (JSONObject) personJson.get("person");
        return new Person((String) personObj.get("First Name"),
                (String) personObj.get("Last Name"),
                (String) personObj.get("City"),
                (String) personObj.get("State"),
                Integer.parseInt(((Long) personObj.get("Zip")).toString()),
                (Long) personObj.get("Phone"));
    }
}
