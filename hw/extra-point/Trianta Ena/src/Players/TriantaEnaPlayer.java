package Players;

import Cards.Card;
import Games.TriantaEna;
import Hands.TriantaEnaHand;
import Wallet.Wallet;

public class TriantaEnaPlayer extends Player{

    private TriantaEnaHand hand;
    public static final String CYAN = "\033[0;36m";

    public TriantaEnaPlayer(){
        super();
        hand = new TriantaEnaHand();
        wallet = new Wallet(100);
    }

    public TriantaEnaPlayer(String name){
        super(name);
        hand = new TriantaEnaHand();
        wallet = new Wallet(100);
    }

    public TriantaEnaPlayer(String name, int balance){
        super(name);
        hand = new TriantaEnaHand();
        wallet = new Wallet(balance);
    }

    public void getCard(Card card){
        hand.addCard(card);
        //System.out.println("\033[0;36m" + this + " gets " + card);
    }

    public void setBet(int bet){
        hand.setBet(bet);
        wallet.pay(bet);
    }

    public int getBet(){
        return hand.getBet();
    }

    public TriantaEnaHand getHand() {
        return hand;
    }

    public boolean isBust(){
        return hand.getMaxTotal() > 31;
    }

    public void renew(){
        hand = new TriantaEnaHand();
    }

}
