package Creatures;

import Items.Weapon;

/**
 * Class represents two hands of hero
 */

public class Hands {

    private Hand[] hands = new Hand[2];

    public Hands(){
        hands[0] = new Hand("left hand");
        hands[1] = new Hand("right hand");
    }

    public Hand[] getHands() {
        return hands;
    }

    public boolean hasEmptyHand(){
        if(hands[0].hasWeapon() && hands[1].hasWeapon()){
            return false;
        } else if(!hands[0].hasWeapon()){
            return true;
        } else if(hands[0].getWeapon().getRequired_hand() == 2){
            return false;
        } else {
            return true;
        }
    }

    public boolean hasOneWeapon(){
        return hands[0].getWeapon().getRequired_hand() == 2;
    }

    public void unequipAll(){
        hands[0].unequip();
        hands[1].unequip();
    }

    public int weakerHand(){
        if(hands[0].getDamage() > hands[1].getDamage()){
            return 1;
        } else {
            return 0;
        }
    }

    public int getDamage(){
        return hands[0].getDamage() + hands[1].getDamage();
    }

    public void equipWeapon(Weapon weapon){
        if(weapon.getRequired_hand() == 1){
            if(hasEmptyHand()){
                if(hands[0].hasWeapon()){
                    hands[1].equip(weapon);
                    System.out.println("\033[0;35m" + weapon + " has equipped on " + hands[1]);
                } else {
                    hands[0].equip(weapon);
                    System.out.println("\033[0;35m" + weapon + " has equipped on " + hands[0]);
                }
            } else if(hasOneWeapon()){
                unequipAll();
                hands[0].equip(weapon);
                System.out.println("\033[0;35m" + weapon + " has equipped on " + hands[0]);
            } else {
                hands[weakerHand()].equip(weapon);
                System.out.println("\033[0;35m" + weapon + " has equipped on " + hands[weakerHand()]);
            }
        } else {
            unequipAll();
            hands[0].equip(weapon);
            System.out.println("\033[0;35m" + weapon + " has equipped!");
        }
    }
}
