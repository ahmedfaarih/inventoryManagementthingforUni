package com.company;

//Item class defines what an item will have.

public class Item {

    //These are the fields of the item class
    public String itemCategory;
    public String itemName;
    public int quantity;

    //Constructor for Item class takes 3 input parameters.
    Item(String categoryName, String itemName, int quantity){
        this.itemCategory = categoryName;
        this.itemName = itemName;
        this.quantity = quantity;
    }

}
