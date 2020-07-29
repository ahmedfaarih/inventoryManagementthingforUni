 package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    This class will have an arrayList of 'employee' objects as its field. This class will also have methods to read text
    file and get all the employees. Further methods are to be added as I need them.
 */

public class EmployeeList {
    public ArrayList<Employee> listOfEmployees = new ArrayList<>();
    private ArrayList<String> listOfDepartments = new ArrayList<>();

    //This method will read a text file and get all the employees to the ArrayList 'listOfEmployees'. Name, department and
    //phone number should be separated by a comma and no space in between in the text file. Otherwise, will show errors.
    public void readFile() throws IOException {
        File file = new File("employee_list.txt");                          //Declaration and initialization of file object
        Scanner textFile = new Scanner(file);                                  //Creates Scanner object and parse in the file

        while(textFile.hasNextLine()){                                         //Stay in a loop until there is no written line in the text file
            String line = textFile.nextLine();                                 //Read line and store in a String variable 'line'
            String[] words = line.split(",");                               //Split the whole line at commas and store those in a simple String array
            Employee employee = new Employee(words[0],words[1],words[2]);      //Create Employee object
            this.listOfEmployees.add(employee);                                //Add created Employee object to listOfEmployees ArrayList
            if(!this.listOfDepartments.contains(words[1])){                    //This just adds all the department names to an ArrayList
                this.listOfDepartments.add(words[1]);
            }
        }
    }

    //This method displays all the employees in the listOfEmployees ArrayList.
    public void DisplayAllEmployees(){
        String format = "%-20s %-20s %-9s";
        System.out.println("\n");
        System.out.printf(format, "|"+"  Name    ", "|"+"  Department   ", "|"+"  phone number|"+"\n");
        System.out.println("----------------------------------------------------------");
        for(Employee employee: listOfEmployees){
            System.out.format(format,"|"+employee.name,"|"+employee.departmentName,"|"+employee.contactNumber+"       |"+ "\n");
        }
    }

    //This is useful to check if there is an employee in the listOfEmployees. Returns a boolean value. (true or false)
    public boolean hasEmployee(Employee i){
        boolean exists = false;

        for(Employee e: listOfEmployees){
            if(e.name.toLowerCase().equals(i.name.toLowerCase())&& e.departmentName.toLowerCase().equals(i.departmentName.toLowerCase()) && e.contactNumber.equals(i.contactNumber)){
                i.name = e.name;
                i.departmentName = e.departmentName;
                exists = true;
            }
        }
        return exists;

    }


    //Method to get the exact name and department name from the listOfEmployees
    public Employee getEmployee(String n, String d, String ph){
        Employee e = new Employee(n,d,ph);
        for(Employee i: listOfEmployees){
            if(i.name.toLowerCase().equals(e.name.toLowerCase()) && i.departmentName.toLowerCase().equals(e.departmentName.toLowerCase()) &&
            i.contactNumber.equals(e.contactNumber)){
                e.name = i.name;
                e.departmentName = i.departmentName;
                e.contactNumber = i.contactNumber;
            }
        }
        return e;
    }

    //This method returns an array of the list of names of department. This is used to ask user to
    //select from this list of departments to get history of items taken from that department.
    public String[] departmentNames(){
        int size = this.listOfDepartments.size();
        String[] deps = new String[size];
        for(int i = 0; i < size; i++){
            deps[i] = this.listOfDepartments.get(i);
        }
        return deps;
    }

}
