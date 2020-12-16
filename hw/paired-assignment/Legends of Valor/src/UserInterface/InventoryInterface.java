package UserInterface;

import Items.Item;
import Stuffs.Inventory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class represents an interface of an inventory
 * Used for interacting with user about the inventory
 */
public class InventoryInterface extends GameIO {

    private Inventory inventory;
    private Scanner scanner;

    public InventoryInterface(Inventory inventory){
        this.inventory = inventory;
        scanner = new Scanner(System.in);
    }

    public InventoryInterface(){}



    public void printInventory(){
        inventory.printInventory();
    }

    public String getItemName(){
        System.out.println(tmd.addColor("purple", "Please enter the name of the item you want to take action:"));
        String name;

        while (true){
            try{
                name = scanner.nextLine();
                if(!(inventory.haveItem(name))){
                    System.out.println(tmd.addColor("red", "No item called " + name));
                } else {
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println(tmd.addColor("red", "Invalid input. Please enter again:"));
                scanner.next();
            }
        }
        return name;
    }

    public String getActionType(){
        String action;
        System.out.println(tmd.addColor("purple", "Please enter the type of action you want to take: (sell/use)"));

        while (true){
            try {
                action = scanner.nextLine();
                if(!action.equalsIgnoreCase("sell") && !action.equalsIgnoreCase("use")){
                    System.out.println(tmd.addColor("red", "Invalid input. Please enter sell/buy"));
                } else {
                    return action;
                }
            } catch (InputMismatchException e){
                System.out.println(tmd.addColor("red", "Invalid input. Please enter again:"));
                scanner.next();
            }
        }
    }

    public void start(){
        printInventory();
        if(inventory.isEmpty()){}
        else{
            if(this.makeConfirmation("take action for an item")){
                Item item = inventory.getItem(getItemName());
                String actionType = getActionType();
                if(actionType.equalsIgnoreCase("use")){
                    item.use(inventory.getOwner());
                } else {
                    item.sell(inventory.getOwner());
                }
                if(this.makeConfirmation("use another item")){
                    start();
                }
            }
        }
    }
}
