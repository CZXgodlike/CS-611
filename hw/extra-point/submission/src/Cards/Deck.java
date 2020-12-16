package Cards;

/*
  Class represents a deck for game
 */

import java.util.Collections;
import java.util.Stack;

public class Deck extends Stack<Card> {

    public Deck(){
        String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
        for(int i = 1; i <= 13; i ++){
            for(String suit: suits){
                this.push(new Card(i, suit));
            }
        }
        shuffleDeck();
    }

    /**
     * Shuffle the deck
     */
    public void shuffleDeck(){
        Collections.shuffle(this);
    }

}

