package UserInterface;

import Creatures.Hero;

import java.util.Scanner;

public class MyScanner {

    protected Scanner scanner;
    protected Manual manual;

    public MyScanner(Hero hero){
        scanner = new Scanner(System.in);
        manual = new Manual(hero);
    }

    public String nextLine(){
        String s = scanner.nextLine();
        if(s.equalsIgnoreCase("m")){
            manual.getAction();
        }
        return s;
    }


    public String next(){
        return scanner.next();
    }

    public int nextInt(){
        return scanner.nextInt();
    }
}
