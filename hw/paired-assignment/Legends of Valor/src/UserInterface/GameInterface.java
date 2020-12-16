package UserInterface;

import Helper.DataBase;
import Helper.ItemFactory;
import Creatures.Hero;
import Items.Spell;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameInterface extends GameIO{

    private Scanner scanner;
    private Graphic graphic;

    public GameInterface(){
        scanner = new Scanner(System.in);
        graphic = new Graphic();
    }

    public void printBanner(){
        System.out.println(graphic.getBanner());
    }

    public void printCastle() {
        System.out.println(graphic.getCastle());
    }

    public void printSkeleton() {
        System.out.println(graphic.getSkeleton());
    }

    public String getHeroName(int i){
        DataBase dataBase = new DataBase();
        dataBase.printHeroList();
        if(i == 0){
            System.out.println(tmd.addColor("purple", "Please enter the name of your first hero:"));
        } else if(i == 1){
            System.out.println(tmd.addColor("purple", "Please enter the name of your second hero:"));
        } else {
            System.out.println(tmd.addColor("purple", "Please enter the name of your third hero:"));
        }
        String name;

        while(true){
            try{
                name = scanner.nextLine();
                if(dataBase.inquire(name).isEmpty()){
                    System.out.println(tmd.addColor("red", "No hero with name " + name + ". Please check your spell and try again:"));
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println(tmd.addColor("red", "Invalid input. Please enter again:"));
                scanner.next();
                continue;
            }
            return name;
        }
    }

    public String getAttackType(Hero hero){
        System.out.println(tmd.addColor("purple", "Please enter your attack type for " + hero + " (normal/spell):"));
        String type;

        while(true){
            try{
                type = scanner.nextLine();
                if(!type.equalsIgnoreCase("spell") && !type.equalsIgnoreCase("normal") && !type.equals(" ")){
                    System.out.println(tmd.addColor("red", "Invalid input. Please enter (normal/spell):"));
                    continue;
                } else if(type.equalsIgnoreCase("spell")){
                    if(hero.getSpells().isEmpty() || !hero.hasAvailableSpell()){
                        System.out.println(tmd.addColor("red", hero+ " doesn't have any spell available!"));
                        continue;
                    }
                }
            } catch (InputMismatchException e){
                System.out.println(tmd.addColor("red", "Invalid input. Please enter again (normal/spell):"));
                scanner.next();
                continue;
            }
            return type;
        }
    }

    public Spell getSpell(Hero hero){
        System.out.println(tmd.addColor("purple", "Please enter the name of the spell you want to use:" + hero.getSpells()));
        String name;
        DataBase dataBase = new DataBase();

        while(true){
            try{
                name = scanner.nextLine();
                if(dataBase.inquire(name).isEmpty()){
                    System.out.println(tmd.addColor("red", "No spell with this name. Please check your spell and try again:"));
                    continue;
                } else if(! hero.hasSpell(name)){
                    System.out.println(tmd.addColor("red", hero + " doesn't have this spell. Please check your spell and try again:"));
                    continue;
                } else if(! hero.canUse(name)){
                    System.out.println(tmd.addColor("red", hero + " can't use this spell. Please enter another one:"));
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println(tmd.addColor("red", "Invalid input. Please enter again:"));
                scanner.next();
                continue;
            }
            ItemFactory itemFactory = new ItemFactory();
            return (Spell) itemFactory.create(name);
        }
    }

}
