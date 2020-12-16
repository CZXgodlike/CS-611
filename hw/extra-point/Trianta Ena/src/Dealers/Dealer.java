package Dealers;

import Cards.Card;
import Cards.Deck;
import Hands.Hand;
import Human.Human;
import Players.Player;
import Players.TriantaEnaPlayer;
import Wallet.Wallet;

import java.util.List;

public abstract class Dealer<T extends Player> extends Human {

    protected Deck deck;
    protected Hand hand;

    public Dealer(){
        deck = new Deck();
    }

    public Dealer(String name){
        super(name);
        deck = new Deck();
    }

    public abstract void dealCards(List<T> players);

    public abstract void dealCardToMyself();

    public void hit(TriantaEnaPlayer player){
        Card card = deck.pop();
        player.getCard(card);
        System.out.println("\033[0;35m" + player + " got " + card);
    }

    public void renewDeck(){
        deck = new Deck();
    }

    @Override
    public String toString() {
        return name;
    }
}
