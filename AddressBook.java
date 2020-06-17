package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        System.out.println("Welcome to Adderess Book program");
        boolean isContinue = true;
        while (isContinue == true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1)ADD   7)EXIT");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    Person person = PersonUtils.record();
                    //System.out.println(person);
                    personList.add(person);
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
