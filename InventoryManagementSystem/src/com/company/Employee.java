package com.company;

//Employee class holds the basic properties/fields of an employee and a constructor.

import java.util.Scanner;

public class Employee {
    //These are the fields/properties of the Employee class
    public String name;
    public String departmentName;
    public String contactNumber;

    //This is the constructor for the Employee class. It requires 3 String type variables as input parameters.
    Employee(String name,String departmentName, String contactNumber){
        this.name = name;
        this.departmentName = departmentName;
        this.contactNumber = contactNumber;
    }

    //Default constructor. Uninitialized variables
    Employee(){
        String name;
        String departmentName;
        String contactNumber;
    }
}
