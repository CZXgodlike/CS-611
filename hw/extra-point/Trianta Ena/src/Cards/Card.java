package Cards;

/**
 * Class represents a single card
 */
public class Card{
    private String suit;
    private String name;
    private int value;


    public Card(){
    }

    /**
     *
     * @param index equals to the value on the card (Jack is 11, Queen is 12 and King is 13)
     * @param suit the card's suit
     */
    public Card(int index, String suit){
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8 ,9, 10, 10, 10, 10};
        String[] names = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

        this.suit = suit;
        setValue(values[index - 1]);
        setName(names[index - 1]);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString() {
        return name + "-" + suit;
    }

    /**
     * Override equals
     * @param card card compare to
     * @return equals or not
     */
    public Boolean equals(Card card) {
        return name.equals(card.name);
    }
}

