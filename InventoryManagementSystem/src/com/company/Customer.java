package com.company;

/*
   Customer class inherits Employee class. (I could have made Customer Class as a separate Class. But I made it so to
   show the concept of inheritance, which is a very important topic in Object Oriented Programming.
   Employee is the parent class of Customer. Which means that Customer will have all the fields of Employee such as
   name,department,phone number and additional fields to store the item taken and the date taken.
*/

import java.util.ArrayList;
import java.util.Date;

public class Customer extends Employee {

    //Fields of Customer class.
    public ArrayList<Item> itemsTaken = new ArrayList<>();
    public ArrayList<Date> itemTakenDate = new ArrayList<Date>();

    //Constructor which takes in 3 Strings and an Item object
    Customer(String name, String departmentName, String contactNumber, Item takenItem, Date date){
        super(name, departmentName, contactNumber);
        this.itemsTaken.add(takenItem);
        this.itemTakenDate.add(date);
    }

    //This is the default constructor. No fields have been initialized.
    Customer(){
        super();
        ArrayList<Item> itemsTaken;
        ArrayList<Date> itemTakenDate;
    }
}
