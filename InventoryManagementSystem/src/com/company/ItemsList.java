package com.company;

/*
This class will have an ArrayList of Item objects and methods to perform operations
on those objects.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemsList {
    //Fields (an ArrayList of Item objects)
    public ArrayList<Item> listOfItems = new ArrayList<>();

    //This method reads a text file called "item_list.txt" and add all items to the ArrayList
    public void ReadItemsFile() throws FileNotFoundException{
        File file = new File("item_list.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] words = line.split(",");
            Item item = new Item(words[0], words[1], Integer.parseInt(words[2]));
            listOfItems.add(item);
        }
    }

    //Method to display all the Item objects in the listOfItems ArrayList.
    public void DisplayAllItems(){
        System.out.println("\n");
        String format = "%-30s %-20s %-15s";
        System.out.println("+--------------------------------------------------------------------+");
        System.out.printf(format,"|   Item Name","|   Category","|   Available Qty"+"|\n");
        System.out.println("+--------------------------------------------------------------------+");
        for(Item item : listOfItems){

            System.out.format(format,"|"+item.itemName, "|"+item.itemCategory,"|\t\t"+item.quantity+"\t\t |");
            System.out.println();

        }

    }

    //This method is used to check if an item is in the list of items
    public boolean contain(String name, String category){
        for(Item item: listOfItems){
            if(item.itemName.toLowerCase().equals(name.toLowerCase()) && item.itemCategory.toLowerCase().equals(category.toLowerCase())){
                return true;
            }
        } return false;
    }


    //This method will update the quantity of the entered item, if that item exists.
    public void updateItem(Item itemToUpdate){
        for(Item item: listOfItems){
            if(item.itemName.equals(itemToUpdate.itemName) && item.itemCategory.equals(itemToUpdate.itemCategory)){
                item.quantity -= itemToUpdate.quantity;
            }
        }
    }

    //This method accept and item name and category, and will return the quantity available
    public int availableQuantity(String name, String category){
        int qty = 0;
        for(Item item: listOfItems){
            if(item.itemName.toLowerCase().equals(name.toLowerCase()) && item.itemCategory.toLowerCase().equals(category.toLowerCase())){
                qty = item.quantity;
            }
        }
        return qty;
    }


    //Method to get the exact Item name and category name but with quantity 0. Created this to accept capital or simple letters from user.
    public Item getItem(String category, String name){
        Item newItem = new Item(category,name,0);
        for(Item item: listOfItems){
            if(item.itemCategory.toLowerCase().equals(category) && item.itemName.toLowerCase().equals(name)){
                newItem.itemCategory = item.itemCategory;
                newItem.itemName = item.itemName;
            }
        }
        return newItem;
    }

}


