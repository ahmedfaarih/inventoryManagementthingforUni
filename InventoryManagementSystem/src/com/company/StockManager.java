package com.company;
/*
 This class is designed to control most of the operations of this program.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class StockManager  {
    // Fields or properties
    private ArrayList<Customer> customerList = new ArrayList<>();
    private ItemsList itemsList = new ItemsList();
    private EmployeeList employeeList = new EmployeeList();
    public Menu menu = new Menu();

    // Method to read item_list.txt file and employee_list.txt file
    public void readFiles()throws Exception{
        this.itemsList.ReadItemsFile();
        this.employeeList.readFile();
    }

    // Method to display all items.
    public void displayItems(){
        this.itemsList.DisplayAllItems();
    }

    //Method to display all employees
    public void displayAllEmployees(){
        this.employeeList.DisplayAllEmployees();
    }

    //This Method returns an Employee object. I created this to avoid repeating same code. Now I can call this function to get all the details
    public Employee setEmployee(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter name:");
        String name = input.nextLine();
        System.out.println("Enter Department name: ");
        String department = input.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = input.nextLine();

        return new Employee(name,department,phoneNumber);
    }

    /*
      This method will check if entered employee details matches any employees in the employee list and check if the entered
      item matches with any items in the item list. If all matches then employee is added as customer and also the item list
      will get updated.
    */
    public void addCustomer(){
        Scanner input = new Scanner(System.in);                                             //Scanner object to get input from user
        System.out.println("\n");
        this.employeeList.DisplayAllEmployees();
        System.out.println("\n");

        //Calls the setEmployee Method which returns an Employee object and assigned it to employeeToBeAdded
        Employee employeeToBeAdded = setEmployee();

        if(this.employeeList.hasEmployee(employeeToBeAdded)){
            System.out.println("\n");
            this.itemsList.DisplayAllItems();
            System.out.println("\nEnter Item category: ");
            String itemCategory = input.next();
            System.out.println("Enter item name: ");
            String itemName = input.next();
            if(itemsList.contain(itemName,itemCategory)){
                Item item = itemsList.getItem(itemCategory,itemName);
                System.out.println("Enter quantity of item: ");
                int quantity = input.nextInt();
                int available = itemsList.availableQuantity(itemName,itemCategory);
                item.quantity = quantity;
                boolean exit = false;
                while(!exit && quantity>0){
                    if(available >= quantity){
                        Date date = new Date();

                        Customer customer = new Customer(employeeToBeAdded.name,employeeToBeAdded.departmentName,employeeToBeAdded.contactNumber,item, date);
                        customerList.add(customer);
                        itemsList.updateItem(item);     //This will update the itemList and deduct what the customer has taken


                        //This is the bin card. (needs work to display in a nice format)
                        System.out.println("\nName: " + customer.name);
                        System.out.println("Item taken: " + item.itemName);
                        System.out.println("Quantity taken: " + item.quantity);
                        System.out.println("Date & time taken: " + date);
                        exit = true;
                    } else {
                        if(available == 0){
                            System.out.println("Item out of stock! Enter 0 to exit");
                            quantity = input.nextInt();
                            item.quantity = quantity;
                        } else{
                            System.out.println("Available quantity is " + available + "\nEnter 0 to exit or enter any value equal to or lower than "
                                    + available + ":");
                            quantity = input.nextInt();
                            item.quantity = quantity;
                        }


                    }
                }

            } else {
                System.out.println("The item is not available!");
            }
        } else {
            System.out.println("Entered name, department and phone number does not match with\n" +
                    "any of the \nEmployees in the list of employees, or you are not an Employee!");

        }

    }


    //Method to display customer's history of taken items
    public void showHistoryByCustomer(){
        Employee employee = setEmployee();
        if(this.employeeList.hasEmployee(employee)){
            employee = employeeList.getEmployee(employee.name,employee.departmentName,employee.contactNumber);
            for(Customer customer: customerList){
                if(customer.name.equalsIgnoreCase(employee.name) &&
                        customer.departmentName.equalsIgnoreCase(employee.departmentName) &&
                        customer.contactNumber.equals(employee.contactNumber)){
                    System.out.println("\n");
                    System.out.println("Item taken by: "+ customer.name+"\tDepartment Name: "+ customer.departmentName+"\tContact Number: "+customer.contactNumber);
                    System.out.println("**********************************************************************************************************");
                    String formatToDisplay = "%-30s %-20s %-6s %-30s";
                    System.out.printf(formatToDisplay,"Item Name","|Category", "|Qty", "|Date Taken");
                    System.out.println();
                    System.out.println("----------------------------------------------------------------------------------");
                    for(Item item: customer.itemsTaken){
                        int index = customer.itemsTaken.indexOf(item);
                        System.out.format(formatToDisplay, item.itemName, "|"+item.itemCategory, "|"+item.quantity, "|"+customer.itemTakenDate.get(index));
                        System.out.println("\n");
                    }
                } else {
                    System.out.println("\n\nYou have not taken any item!");
                }

            }
        } else {
            System.out.println("\n\nEntered name, department and phone number does not match with " +
                    "any of the \nEmployees in our records OR you are not an employee!");
        }
        if(this.customerList.isEmpty()){
            System.out.println("\n\nNO EMPLOYEE HAS TAKEN ANY ITEM FROM THE STOCK!");
        }

    }

    //Method to view the items taken by all the employees of the selected department
    public void showHistoryByDepartment(){
        Scanner input = new Scanner(System.in);
        String[] departments = this.employeeList.departmentNames();
        menu.displayDepartments(departments);
        System.out.println("Enter name from list: ");
        String depName = input.next();
        boolean matchNotFound = true;
        boolean exit = false;

        while(matchNotFound && !exit){
            for(int i = 0; i < departments.length; i++){
                if(departments[i].toLowerCase().equals(depName.toLowerCase())){
                    matchNotFound = false;
                    for(Customer customer: customerList){
                        if(customer.departmentName.equalsIgnoreCase(depName)){
                            for(Item item: customer.itemsTaken){
                                int index = customer.itemsTaken.indexOf(item);
                                System.out.println("\n\nItem taken from Department: " + customer.departmentName);
                                System.out.println("*****************************************************************************");
                                System.out.println("Employee Name: " + customer.name + "\t\tItem Name: " + item.itemName
                                        + "\t\tTaken quantity: " + item.quantity + "\t\tDate taken: " + customer.itemTakenDate);

                            }
                        } else {
                            System.out.println("No employee from this department has taken any Item from the stock!");
                        }
                    }

                }
            }
            if(matchNotFound){
                menu.displayDepartments(departments);
                System.out.println("Invalid name! Type 'exit' to go back to main menu. OR");
                depName = input.next();
                if(depName.equals("exit")){
                    exit = true;
                }
            }

        }

    }
}
