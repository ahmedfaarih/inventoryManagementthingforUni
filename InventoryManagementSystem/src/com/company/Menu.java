package com.company;
/*This class will have all the methods which display menus.

PS: Notice that there are no fields for this class. This is because
there is no need for fields as of yet.
*/

public class Menu {

    //This method will display the main menu
    public void displayMain(){
        System.out.println("\n\n");
        System.out.println("==========================================");
        System.out.println("|          STOCK MANAGING SYSTEM         |");
        System.out.println("==========================================");
        System.out.println("|                                        |");
        System.out.println("| Options:                               |");
        System.out.println("|        1. Display all Items            |");
        System.out.println("|        2. Take Item                    |");
        System.out.println("|        3. Withdrawals by Employee      |");
        System.out.println("|        4. Withdrawals by Department    |");
        System.out.println("|        5. Display all employees        |");
        System.out.println("|                                        |");
        System.out.println("| Type 'exit' to quit!                   |");
        System.out.println("|                                        |");
        System.out.println("==========================================");
        System.out.print("Option: ");
    }

    public void displayDepartments(String[] names){
        int size = names.length;
        System.out.println("\n");
        System.out.println("Departments");
        System.out.println("-----------");
        for(int i = 0; i < size; i++){
            System.out.println("\t"+names[i]);
        }
        System.out.println();

    }
}
