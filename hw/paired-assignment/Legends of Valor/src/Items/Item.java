package Items;

import Creatures.Hero;

import java.util.List;
import java.util.Objects;

/**
 * Abstract class represents an item
 * An item can be an armor, a spell, a weapon or a potion
 */

public abstract class Item implements Sellable {
    protected String name;
    protected int price;
    protected int minLevel;

    public Item(){}

    public Item(List<String> list){
        this.name = list.get(0);
        this.price = Integer.parseInt(list.get(1));
        this.minLevel = Integer.parseInt(list.get(2));
    }

    public boolean isBuyable(int balance, int level){
        if(level < minLevel){
            System.out.println("\033[0;31m" + "Your level is too low!");
            return false;
        } else if(balance < price){
            System.out.println("\033[0;31m" + "No enough balance!");
            return false;
        } else{
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public void use(Hero hero){}

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }

    public void sell(Hero hero){
        hero.getInventory().remove(name);
        hero.getWallet().earn(price/2);
        System.out.println("\033[0;35m" + "Successfully sold " + this + ". " + hero.getWallet());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

}
