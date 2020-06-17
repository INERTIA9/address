package com.bridgelabz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        System.out.println("Welcome to Adderess Book program");
        boolean isContinue = true;
        while (isContinue == true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1)ADD  2)EDIT   7)EXIT");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    Person person = PersonUtils.record();
                    if(personList.contains(person)){
                        System.out.println("Person Already Exist :"+ person.getFname() + " " + person.getLname());
                    }else{
                        person = PersonUtils.reuseRecord(person);
                        personList.add(person);
                    }

                    //System.out.println(person);

                    //System.out.println(personList.size());
                    break;
                case 2:
                    Person personEdit = PersonUtils.record();
                    if(personList.contains(personEdit)){
                       // System.out.println("Person Already Exist :"+ personEdit.getFname() + " " + personEdit.getLname());
                         String flName = personEdit.getFname()+personEdit.getLname();
                        personEdit = PersonUtils.reuseRecord(personEdit);
                        Iterator<Person> personIterator = personList.iterator();
                        while(personIterator.hasNext()){
                            Person person1 = personIterator.next();
                            if(flName.equals(person1.getFname()+person1.getLname())){
                                personIterator.remove();
                            }
                        }
                        personList.add(personEdit);
                    }else{
                        System.out.println("Person Does not Exist :"+ personEdit.getFname() + " " + personEdit.getLname());
                    }

                    //System.out.println(person);

                    //System.out.println(personList.size());
                    break;
                case 7:
                    isContinue = false;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }


        }
    }
}
