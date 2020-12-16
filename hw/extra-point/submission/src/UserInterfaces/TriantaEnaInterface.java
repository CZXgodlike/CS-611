package UserInterfaces;

import Assets.TriantaEnaGraphics;
import Cards.Card;
import Dealers.TriantaEnaBanker;
import Games.TriantaEna;
import Players.TriantaEnaPlayer;

import java.util.*;

public class TriantaEnaInterface extends GameInterface{

    protected TriantaEnaGraphics graphics;

    public TriantaEnaInterface(){
        super();
        graphics = new TriantaEnaGraphics();
    }

    public TriantaEnaInterface(TriantaEna triantaEna){
        super();
        game = triantaEna;
        graphics = new TriantaEnaGraphics();
    }

    public List<TriantaEnaPlayer> createPlayers(int minPlayer, int maxPlayer){
        printMessage("yellow", "-".repeat(50));
        printMessage("yellow", "First, please enter the number of players (include dealer):");
        Scanner sc = new Scanner(System.in);
        int number;

        while(true){
            try {
                number = sc.nextInt();
                if(number > maxPlayer || number < minPlayer){
                    printMessage("red", "Please enter a number between " + minPlayer + " and " + maxPlayer+ ":");
                    continue;
                }
            } catch (InputMismatchException e){
                printMessage("red", "Invalid input! Please enter a number between " + minPlayer + " and " + maxPlayer + ":");
                sc.next();
                continue;
            }
            List<TriantaEnaPlayer> players = new ArrayList<TriantaEnaPlayer>();
            for(int i = 0; i < number; i ++){
                players.add(new TriantaEnaPlayer("player " + (i + 1))) ;
            }
            return players;
        }
    }


    public void announceCardsForPlayer(List<TriantaEnaPlayer> players){
        for(TriantaEnaPlayer player: players){
            printMessage("purple", player + " got " + player.getHand().getCards().get(0));
        }
    }

    public void announceCardForBanker(TriantaEnaBanker banker){
        printMessage("purple", banker + "(banker) got " + banker.getHand().getCards().get(0));
    }

    public void setBetForPlayers(List<TriantaEnaPlayer> players){
        printMessage("yellow", "-".repeat(50));
        printMessage("yellow", "Now please set your bets: ");
        Scanner sc = new Scanner(System.in);

        for(TriantaEnaPlayer player: players){
            int bet;
            printMessage("yellow", player + ", please set your bet:");

            while(true){
                try{
                    bet = sc.nextInt();
                    if(bet > player.getBalance()){
                        printMessage("red", "You don't have enough balance. Please try a smaller bet:");
                    } else if(bet <= 0){
                        printMessage("red", "Please enter a larger bet!");
                    }else {
                        break;
                    }
                } catch (InputMismatchException e){
                    printMessage("red", "Invalid input! Please try again:");
                }
            }
            player.setBet(bet);
            printMessage("blue", player + " successfully set a bet of $" + bet);
        }
    }

    public void getAction(TriantaEnaBanker banker, TriantaEnaPlayer player){
        Scanner scanner = new Scanner(System.in);
        String action;
        printMessage("yellow", player + ", please enter your action (hit/stand): (current cards: " + player.getHand() + ")");

        while(true){
            try{
                action = scanner.nextLine();
                if(action.equalsIgnoreCase("hit")){
                    banker.hit(player);
                    if(player.isBust()){
                        printMessage("red", player + " is bust!");
                        break;
                    }
                    printMessage("yellow", "Please enter your next action (hit/stand): (current cards: " + player.getHand() + ")");
                } else if(action.equalsIgnoreCase("stand")){
                    break;
                }
            } catch (Exception e){
                printMessage("red", "Invalid input. Please try again.");
            }
        }
        printMessage("blue", player + " finally gets " + player.getHand() + " with total:" + player.getHand().getMaxTotal());
    }

    public void announcePlayersFinalCards(List<TriantaEnaPlayer> players){
        for(TriantaEnaPlayer player: players){
            printMessage("purple", player + " finally gets " + player.getHand() + " with total:" + player.getHand().getMaxTotal());
        }
    }


    public void announceBankerFinalCards(TriantaEnaBanker banker){
        printMessage("purple", banker + "(banker) finally gets " + banker.getHand() + " with total:" + banker.getHand().getMaxTotal());
    }

    public void printBanner(){
        printMessage("red", graphics.getBanner());
    }

    public void printWelcomeMsg(){
        printMessage("yellow", graphics.getWelcomeMsg());
    }

    public void printRotate(){
        printMessage("green", graphics.getRotate());
    }

}



