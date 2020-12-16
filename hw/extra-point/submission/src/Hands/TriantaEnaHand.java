package Hands;

import Cards.Card;
import Hands.Hand;

import java.util.ArrayList;

public class TriantaEnaHand extends Hand {

    public TriantaEnaHand(){
        cards = new ArrayList<>();
    }

    /**
     * Judge if this hand has ACE
     * @return has or not
     */
    public Boolean hasAce(){
        for(Card card: cards){
            if(card.getName().equals("Ace")){
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate the total
     * assume that ACE is 1
     * Mainly used for helper func
     * @return the total with ACE as 1
     */
    public int getMinTotal(){
        int total = 0;
        for(Card card: cards){
            total += card.getValue();
        }
        return total;
    }

    /**
     * Calculate the max total within 21
     * intelligently decide the value of ACE
     * @return the total
     */
    public int getMaxTotal(){
        int total = 0;
        for(Card card: cards){
            total += card.getValue();
        }
        if(hasAce() && total > 31){
            return total - 10;
        }
        return total;
    }

    public boolean isBust(){
        return this.getMaxTotal() > 31;
    }

    public boolean hasNaturalTriantaEna(){
        return this.getMaxTotal() == 31 && this.getCards().size() == 3;
    }

    /**
     * Compare player's hand to banker's hand
     * @param hand
     * @return
     */
    public boolean isLargerThan(TriantaEnaHand hand){
        if(this.isBust()){
            return false;
        } else if (hand.isBust()){
            return true;
        } else if (this.getMaxTotal() > hand.getMaxTotal()){
            return true;
        } else return this.hasNaturalTriantaEna() && !hand.hasNaturalTriantaEna();
    }

    /*
    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    *//**
     * Judge if the hand busts
     * @return the hand busts or not
     *//*
    public Boolean isBust(){
        return getMinTotal() > 21;
    }

    *//**
     * Judge if the hand has natural BJ
     * @return has or not
     *//*
    public Boolean hasNaturalBlackJack(){
        return this.size() == 2 && getMaxTotal() == 21;
    }

    *//**
     * Judge if the hand can split
     * @return can or cannot
     *//*
    public Boolean canSplit(){
        return this.size() == 2 && get(0).equals(get(1));
    }


    public String toString() {
        return super.toString();
    }


    *//**
     * Override compareTo(), compare dealer's hand of cards to player's
     * Only used for dealer's hand
     * @param hand
     * @return
     *//*
    public int compareTo(Hand hand) {
        if(total > 21){
            if (hand.isBust()){
                return 0;
            }
            else {
                return -1;
            }
        } else if (hand.isBust()){
            return 1;
        } else if(total > hand.getMaxTotal()){
            return 1;
        } else if(total < hand.getMaxTotal()){
            return -1;
        } else {
            if(hasNaturalBlackJack()){
                if(hand.hasNaturalBlackJack()){
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else {
                if(hand.hasNaturalBlackJack()){
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }*/


}

