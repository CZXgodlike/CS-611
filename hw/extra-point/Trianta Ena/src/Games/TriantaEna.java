package Games;

import Dealers.TriantaEnaBanker;

import Human.Human;
import Players.*;
import UserInterfaces.TriantaEnaInterface;

public class TriantaEna extends CardGame {

    private TriantaEnaBanker banker;
    private TriantaEnaPlayers players;
    private TriantaEnaInterface gameInterface;

    public TriantaEna(){
        gameInterface = new TriantaEnaInterface(this);
    }

    private void initializePlayers(){
        players = new TriantaEnaPlayers(gameInterface.createPlayers(3,8));
        gameInterface.setPlayersNames(players);
    }

    private void randomlySelectBanker(){
        Player banker = players.popRandomPlayer();
        this.banker = new TriantaEnaBanker(banker.getName());
//        for(TriantaEnaPlayer player: players.getPlayers()){
//            if(player.getName().equals(banker.getName()))
//                players.getPlayers().remove(player);
//                break;
//        }
        gameInterface.printMessage("blue", banker + " has been selected as dealer!");
    }

    private void checkOut(){
        gameInterface.printMessage("yellow", "Checking out...");
        for(TriantaEnaPlayer player: players.getPlayers()){
            if(player.getHand().isLargerThan(banker.getHand())){
                player.earn(player.getBet() * 2);
                banker.lose(player.getBet());
                gameInterface.printMessage("green", player + " earns $" + player.getBet() + ". Current balance: " + player.getWallet());
            } else {
                gameInterface.printMessage("red", player + " loses $" + player.getBet() + ". Current balance: " + player.getWallet());
                banker.earn(player.getBet());
            }
        }
    }

    private void rotateBanker(){
        TriantaEnaPlayer rich = players.richestPlayer();
        if(rich != null && rich.getBalance() > banker.getBalance()){
            TriantaEnaBanker newBanker = new TriantaEnaBanker(rich.getName(), rich.getBalance());
            players.getPlayers().remove(rich);
            TriantaEnaPlayer newPlayer = new TriantaEnaPlayer(banker.getName(), banker.getBalance());
            players.getPlayers().add(newPlayer);
            banker = newBanker;
            gameInterface.printRotate();
            gameInterface.printMessage("blue", rich + " has become banker!");
        }
    }

    private void updatePlayer() {
        while (players.hasBankruptPlayer()) {
            for (TriantaEnaPlayer player : players.getPlayers()) {
                if (player.isBankrupt()) {
                    gameInterface.printMessage("red", player + " has left because of bankrupt!");
                    players.getPlayers().remove(player);
                    break;
                }
            }
        }
    }

    public void renew(){
        gameInterface.printMessage("yellow", "Reinitializing players...");
        for(TriantaEnaPlayer player: players.getPlayers()){
            player.renew();
        }
        banker.renew();
    }

    @Override
    public void start() {
        gameInterface.printBanner();
        gameInterface.printWelcomeMsg();
        initializePlayers();
        randomlySelectBanker();

        while(true){
            gameInterface.printMessage("yellow", "\nNew round starts!");
            gameInterface.printMessage("yellow", "-".repeat(50));
            gameInterface.printMessage("yellow", "Banker is dealing the first cards...");
            banker.dealCards(players.getPlayers());
            banker.dealCardToMyself();
            gameInterface.announceCardsForPlayer(players.getPlayers());
            gameInterface.announceCardForBanker(banker);
            gameInterface.setBetForPlayers(players.getPlayers());
            gameInterface.printMessage("yellow", "-".repeat(50));
            gameInterface.printMessage("yellow", "Banker is dealing the rest two cards...");
            banker.dealCards(players.getPlayers());
            banker.dealCards(players.getPlayers());
            gameInterface.announcePlayersFinalCards(players.getPlayers());
            for(TriantaEnaPlayer player: players.getPlayers()){
                gameInterface.getAction(banker, player);
            }
            gameInterface.printMessage("yellow", "-".repeat(50));
            gameInterface.printMessage("yellow", "Banker is hitting...");
            banker.continueHitting();
            gameInterface.announceBankerFinalCards(banker);
            checkOut();
            rotateBanker();
            banker.renewDeck();
            updatePlayer();
            if(players.getPlayers().size() == 0){
                gameInterface.printMessage("red", "No player left! Game ends!");
                break;
            }
            renew();
        }
    }
}
