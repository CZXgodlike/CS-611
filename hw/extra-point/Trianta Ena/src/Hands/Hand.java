package Hands;

import Cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

    protected List<Card> cards;
    protected int bet;

    public Hand(){
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\033[0;37m");
        for(Card card: cards){
            s.append(card);
            s.append(" ");
        }
        return s.toString();
    }
}
