package com.bridgelabz;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        System.out.println("Welcome to Adderess Book program");
        boolean isContinue = true;
        while (isContinue == true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1)ADD   2)EDIT   3)DELETE  4)Sort By Name  5)Sort By City  6)Sort By State  7)Sort By Zip  8)Search by City & State  9)EXIT");
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
                    break;
                case 2:
                    Person personEdit = PersonUtils.record();
                    if(personList.contains(personEdit)){
                         String flName = personEdit.getFname()+personEdit.getLname();
                        Iterator<Person> personIterator = personList.iterator();
                        while(personIterator.hasNext()){
                            Person person1 = personIterator.next();
                            if(flName.equals(person1.getFname()+person1.getLname())){
                                person1 = PersonUtils.reuseRecord(person1);
                            }
                        }
                    }else{
                        System.out.println("Person Does not Exist :"+ personEdit.getFname() + " " + personEdit.getLname());
                    }
                    break;
                case 3:
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
                    break;
                case 4:
                    personList = personList.stream().sorted((p1 , p2) -> p1.getFname().compareTo(p2.getFname())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                case 5:
                    personList = personList.stream().sorted((p1 , p2) -> p1.getCity().compareTo(p2.getCity())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                case 6:
                    personList = personList.stream().sorted((p1 , p2) -> p1.getState().compareTo(p2.getState())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                case 7:
                    personList = personList.stream().sorted((p1 , p2) -> p1.getZip().compareTo(p2.getZip())).collect(Collectors.toList());
                    personList.stream().forEach(p->{
                        System.out.println(p);
                    });
                    break;
                case 8:
                    sc.nextLine();
                    System.out.println("Enter the City");
                    String city = sc.nextLine();
                    System.out.println("Enter the State");
                    String state = sc.nextLine();
                    personList.stream().forEach(p->{
                        if(p.getCity().equals(city) && p.getState().equals(state)){
                            System.out.println(p);
                        }
                    });
                    break;
                case 9:
                    isContinue = false;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }

            System.out.println(personList.size());
           /* personList.stream().forEach(p->{
                System.out.println(p);
            });*/
        }
    }
}
