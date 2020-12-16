package Creatures;

import Items.Weapon;

/**
 * Represents a hand of hero
 */

public class Hand {
    private String name;
    private Weapon weapon = null;

    public Hand(){}

    public Hand(String name){
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean hasWeapon(){
        return weapon != null;
    }

    public void equip(Weapon weapon){
        this.weapon = weapon;
    }

    public void unequip(){
        this.weapon = null;
    }

    public int getDamage(){
        if(weapon == null){
            return 0;
        } else {
            return weapon.getDamage();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
