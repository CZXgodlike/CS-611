package UserInterfaces;

import Games.Game;
import Players.Player;
import Players.Players;
import Players.TriantaEnaPlayer;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class GameInterface{

    protected Game game;
    protected TextDecorator textDecorator;

    public GameInterface() {
        textDecorator = new TextDecorator();
    }

    public void printMessage(String color, String s){
        System.out.println(textDecorator.addColor(color, s));
    }

    public void setPlayersNames(Players players){
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < players.getPlayerNumber(); i++){
            Player player = players.getPlayer(i);
            printMessage("yellow", "Please enter your name for " + player);
            String name;

            while(true){
                try {
                    name = scanner.nextLine();
                    if(players.nameToken(name, i))
                        printMessage("red", "Name taken! Please try another one: ");
                    else
                        break;
                } catch (NoSuchElementException e) {
                    System.out.println("Invalid input! Please try again:");
                }
            }
            player.setName(name);
        }
    }
}
