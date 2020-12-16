package Items;

import Creatures.Hero;

import java.util.List;

/**
 * Class represents a weapon
 */

public class Weapon extends Item {
    private int damage;
    private int required_hand;

    public Weapon(){
        super();
    }

    public Weapon(List<String> list){
        super(list);
        this.damage = Integer.parseInt(list.get(3));
        this.required_hand = Integer.parseInt(list.get(4));
    }

    public int getRequired_hand() {
        return required_hand;
    }

    public int getDamage() {
        return damage;
    }

    public void use(Hero hero){
        hero.getHands().equipWeapon(this);
        hero.getInventory().remove(name);
    }
}
