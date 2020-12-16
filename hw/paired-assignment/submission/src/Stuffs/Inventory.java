package Stuffs;

import Helper.DataBase;
import Creatures.Hero;
import Items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents an inventory of heroes
 */
public class Inventory {

    private List<Item> items;
    private Hero owner;

    public Inventory(Hero owner){
        this.owner = owner;
        items = new ArrayList<>();
    }

    public Inventory(){}

    public Hero getOwner() {
        return owner;
    }

    public void printInventory(){
        DataBase dataBase = new DataBase();
        if(!isEmpty()){
            System.out.println("\033[0;33m" + owner + "'s inventory:");
            System.out.println("\u2012".repeat(50));
            for(Item item: items){
                dataBase.printInformation(item.getName());
            }
            System.out.println("\n");
        } else {
            System.out.println("\033[0;31m" + owner + "'s inventory is empty!");
        }
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public boolean haveItem(String name){
        for(Item item: items){
            if(item.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public Item getItem(String name){
        for(Item item: items){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }

    public void add(Item item){
        items.add(item);
    }

    public void remove(String name){
        for(Item item: items){
            if(item.getName().equals(name)){
                items.remove(item);
                break;
            }
        }
    }

}
