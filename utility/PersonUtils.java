package com.bridgelabz.utility;

import com.bridgelabz.model.Person;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonUtils {
    //method to create person object and set the vlaues
    public static Person record() {
        Person person = new Person();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First Name");
        person.setFname(sc.nextLine());
        System.out.println("Enter the Last Name");
        person.setLname(sc.nextLine());
        return person;
    }
    //method to set values
    public static Person extraRecord(Person person) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the City");
            person.setCity(sc.nextLine());
            System.out.println("Enter the State");
            person.setState(sc.nextLine());
            System.out.println("Enter the 6 Digit Zip code");
            int zip = sc.nextInt();

            int length = (int) (Math.log10(zip) + 1);
            while (length != 6) {
                System.out.println("enter 6 digit number");
                zip = sc.nextInt();
                length = (int) (Math.log10(zip) + 1);
                if (length == 6) {
                    break;
                }
            }

            person.setZip(zip);
            System.out.println("Enter the 10 Digit Mobile Number");
            long pnum = sc.nextLong();
            length = (int) (Math.log10(pnum) + 1);
            while (length != 10) {
                System.out.println("enter 10 digit number");
                pnum = sc.nextLong();
                length = (int) (Math.log10(pnum) + 1);
                if (length == 10) {
                    break;
                }
            }
            person.setPnum(pnum);
        }catch (NullPointerException | InputMismatchException e){
            System.out.println(e);
        }
        return person;
    }
    //method for add person
    public static void addPerson(List<Person> personList){
        Person person = PersonUtils.record();
        String flName = person.getFname()+person.getLname();
        Boolean valid = PersonUtils.stringChecker(flName);
        if(valid) {
            if (personList.contains(person)) {
                System.out.println("Person Already Exist :" + person.getFname() + " " + person.getLname());
            } else {
                person = PersonUtils.extraRecord(person);
                personList.add(person);
            }
        }else {
            System.out.println("Enter valid name -> Enter only character");
        }
    }
    //method for edit person
    public static void editPersonRecord(List<Person> personList){
        Person personEdit = PersonUtils.record();
        if(personList.contains(personEdit)){
            String flName = personEdit.getFname()+personEdit.getLname();
            Iterator<Person> personIterator = personList.iterator();
            while(personIterator.hasNext()){
                Person person1 = personIterator.next();
                if(flName.equals(person1.getFname()+person1.getLname())){
                    person1 = PersonUtils.extraRecord(person1);
                }
            }
        }else{
            System.out.println("Person Does not Exist :"+ personEdit.getFname() + " " + personEdit.getLname());
        }
    }
    //method for delete person
    public static void deletePersonRecord(List<Person> personList){
        Person personDelete = PersonUtils.record();
        if(personList.contains(personDelete)){
            String flName = personDelete.getFname()+personDelete.getLname();
            Iterator<Person> personIterator = personList.iterator();
            while(personIterator.hasNext()){
                Person person1 = personIterator.next();
                if(flName.equals(person1.getFname()+person1.getLname())){
                    personIterator.remove();
                }
            }
        }else{
            System.out.println("Person Does not Exist :"+ personDelete.getFname() + " " + personDelete.getLname());
        }
    }
    //method for search by city and state
    public static void searchByCityState(List<Person> personList){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the City");
        String city = sc.nextLine();
        System.out.println("Enter the State");
        String state = sc.nextLine();
        personList.stream().forEach(p->{
            if(p.getCity().equals(city) && p.getState().equals(state)){
                System.out.println(p);
            }
        });
    }
    //method for search by city
    public static void searchByCity(Map<String,List<Person>> cityMap){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the City");
        String city1 = sc.nextLine();
        if(cityMap.containsKey(city1)){
            cityMap.get(city1).stream().forEach(p->{
                if(p.getCity().equals(city1)){
                    System.out.println(p);
                }
            });
        }else{
            System.out.println("No Person found with city : "+city1);
        }
    }
    //method for search by state
    public static void searchByState(Map<String,List<Person>> stateMap){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the State");
        String state1 = sc.nextLine();
        if(stateMap.containsKey(state1)){
            stateMap.get(state1).stream().forEach(p->{
                if(p.getState().equals(state1)){
                    System.out.println(p);
                }
            });
        }else{
            System.out.println("No Person found with state : "+state1);
        }
    }

    public static boolean stringChecker(String checkString){
        Pattern stringChecker = Pattern.compile("([a-zA-Z]+)");
        Matcher matchString = stringChecker.matcher(checkString);
        boolean validString = matchString.matches();
        return validString;
    }
}