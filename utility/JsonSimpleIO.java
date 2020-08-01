package com.bridgelabz.utility;

import com.bridgelabz.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSimpleIO {

    public List<Person> getDataInList(String filePath) {
        List<Person> addressBookList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(filePath);
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

    public void writeToJson(List<Person> personList, String filePath) {
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

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
