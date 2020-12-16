package Dealers;

import Cards.Card;
import Cards.Deck;
import Games.TriantaEna;
import Hands.TriantaEnaHand;
import Players.Player;
import Players.TriantaEnaPlayer;
import Wallet.Wallet;

import java.util.List;

public class TriantaEnaBanker extends Dealer<TriantaEnaPlayer>{

    protected TriantaEnaHand hand;

    public TriantaEnaBanker(){
        super();
        hand = new TriantaEnaHand();
        wallet = new Wallet(300);
    }


    public TriantaEnaBanker(String name){
        super(name);
        hand = new TriantaEnaHand();
        wallet = new Wallet(300);
    }

    public TriantaEnaBanker(String name, int balance){
        super(name);
        hand = new TriantaEnaHand();
        wallet = new Wallet(balance);
    }

    public TriantaEnaHand getHand(){
        return hand;
    }

    public void dealCards(List<TriantaEnaPlayer> players) {
        for(TriantaEnaPlayer player: players){
            player.getCard(deck.pop());
        }
    }

    public void renew(){
        deck = new Deck();
        hand = new TriantaEnaHand();
    }

    @Override
    public void dealCardToMyself() {
        this.hand.addCard(deck.pop());
    }

    public void continueHitting(){
        while(hand.getMaxTotal() < 27){
            hand.addCard(deck.pop());
        }
    }


}
