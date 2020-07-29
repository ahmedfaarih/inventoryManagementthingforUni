package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        StockManager myStockManager = new StockManager();  // Creates StockManager object called 'myStockManager'
        myStockManager.readFiles();                        // This is to read item_list.txt and store all items in an ArrayList

        Scanner input = new Scanner(System.in);            // Creates Scanner object called 'input' to get input from user
        String option;                                     // This variable is used to store input from the user
        boolean exit = false;

        myStockManager.menu.displayMain();                 // Displays main menu

        option = input.next();                         // Get input from user and store it in 'option' variable

        while(valid(option) || !exit){                               // This while loop will continue until option is '6'
            switch(option){
                case "1":
                    myStockManager.displayItems();
                    myStockManager.menu.displayMain();
                    option = input.next();
                    break;

                case "2":
                    myStockManager.addCustomer();
                    myStockManager.menu.displayMain();
                    option = input.next();
                    break;

                case "3":
                    myStockManager.showHistoryByCustomer();
                    myStockManager.menu.displayMain();
                    option = input.next();
                    break;

                case "4":
                    myStockManager.showHistoryByDepartment();
                    myStockManager.menu.displayMain();
                    option = input.next();
                    break;

                case "5":
                    myStockManager.displayAllEmployees();
                    myStockManager.menu.displayMain();
                    option = input.next();
                    break;

                case "exit":
                    exit = true;
                    break;

                default:
                    System.out.println("\nINVALID INPUT!!");
                    System.out.print("\nType 'exit' to quit OR\nEnter option: ");
                    option = input.next();
            }
        }


    }

    private static boolean valid(String opt){
        boolean val= false;
        String[] options = {"1","2","3","4","5"};
        for (String n:options
             ) {
            if(n.equals(opt)){
                val = true;
            }
        }
        return val;
    }


}
