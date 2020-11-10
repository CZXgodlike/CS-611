package UserInterface;

import Game.Legends;
import Heros.Hero;
import Stuffs.Inventory;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class represents a manual for the game
 * User can access inventory and hero stats through the manual
 * Also including quiting the game and getting OI
 */
public class Manual implements checkYesOrNo{

    private final Legends legends;
    private final Scanner scanner;
    private static final String yellow = "\033[0;33m";
    private static final String red = "\033[0;31m";

    public Manual(Legends legends){
        this.legends = legends;
        scanner = new Scanner(System.in);
    }

    public void printManual(){
        System.out.println(yellow + "Manual instruction: i - open inventory, s - show hero stats, h - helper, c - close manual, q - quit game");
        System.out.println("\u2012".repeat(120));
    }

    public Inventory getInventory(){
        System.out.println(yellow + "Which hero's inventory you want to open?" + Arrays.toString(legends.getPlayer().getHeroes()));
        String hero;

        while (true){
            try{
                hero = scanner.nextLine();
                if(legends.getPlayer().hasHero(hero)){
                    return legends.getPlayer().getHero(hero).getInventory();
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again:");
                scanner.next();
            }
        }
    }

    public void getAction() {
        printManual();
        System.out.println(yellow + "Please enter your action:");
        String input;

        while(true){
            try{
                input = scanner.nextLine();
                if(input.equalsIgnoreCase("c")){
                    return;
                } else if(input.equalsIgnoreCase("i")){
                    Inventory inventory;
                    if(legends.getPlayer().getHeroes().length == 1){
                        inventory = legends.getPlayer().getHeroes()[0].getInventory();
                    } else {
                        inventory = getInventory();
                    }
                    InventoryInterface ii = new InventoryInterface(inventory);
                    ii.start();
                    getAction();
                    break;
                } else if(input.equalsIgnoreCase("q")){
                    if(yesOrNo("quit game")){
                        System.exit(1);
                    }
                } else if(input.equalsIgnoreCase("s")){
                    for(Hero hero: legends.getPlayer().getHeroes()){
                        hero.printStat();
                    }
                    getAction();
                    break;
                } else if(input.equalsIgnoreCase("h")){
                    System.out.println(new Graphic().getHelper());
                    getAction();
                    break;
                } else {
                    System.out.println(red + "Invalid input. Please try again: ");
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again:");
                scanner.next();
            }
        }
    }
}
