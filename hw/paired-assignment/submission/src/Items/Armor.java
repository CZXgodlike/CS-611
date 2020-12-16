package Items;

import Creatures.Hero;

import java.util.List;

/**
 * Class represents an armor
 */

public class Armor extends Item{
    private int damage_reduction;

    public Armor(){}

    public Armor(List<String> list){
        super(list);
        this.damage_reduction = Integer.parseInt(list.get(3));
    }

    public int getDamage_reduction() {
        return damage_reduction;
    }

    public void use(Hero hero){
        hero.putOnArmor(this);
    }
}
