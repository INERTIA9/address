package com.bridgelabz;

import java.util.Scanner;

public class PersonUtils {

    public static Person record() {
        Person person = new Person();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First Name");
        person.setFname(sc.nextLine());
        System.out.println("Enter the Last Name");
        person.setLname(sc.nextLine());
        //reuseRecord(person);
        return person;
    }

    public static Person reuseRecord(Person person) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the City");
        person.setCity(sc.nextLine());
        // pr1.city = sc.nextLine();
        System.out.println("Enter the State");
        person.setState(sc.nextLine());
        // pr1.state = sc.nextLine();
        person.setCityState(person.getCity()+person.getState());
        System.out.println("Enter the 6 Digit Zip code");
        int zip = sc.nextInt();
        // pr1.zip = sc.nextInt();
        int length = (int) (Math.log10(zip) + 1);
        while (length != 6) {
            System.out.println("enter 6 digit number");
            zip = sc.nextInt();
            length = (int) (Math.log10(zip) + 1);
            if (length == 6) {
                // zip=zip;
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
                // pr1.pnum=pr1.pnum;
                break;
            }
        }
        person.setPnum(pnum);
        return person;
    }
}

