package Creatures;

import Items.Spell;
import Stuffs.Tracker;

import java.util.List;

/**
 * Abstract class represents a monster
 * A monster can be a dragon, an exoskeleton or a spirit
 */
public abstract class Monster extends Creature{

    protected int healthPower;
    protected int damage;
    protected int defense;
    protected int dodgeChance;

    public Monster(List<String> list){
        super(list);
        level = Integer.parseInt(list.get(1));
        healthPower = 100 * level;
        damage = Integer.parseInt(list.get(2));
        defense = Integer.parseInt(list.get(3));
        dodgeChance = Integer.parseInt(list.get(4));
    }
    public Monster(){}


    public void attack(Hero hero){
        System.out.println("\033[0;34m" + this + " fights back! " + this + " makes a normal attack on " + hero);
        hero.getAttacked((int) (damage * 0.05));
    }

    public void getAttacked(int damage){
        double i = Math.random();
        if(i > dodgeChance*0.01){
            damage -= defense * 0.05;
            if(damage > 0){
                healthPower -= damage;
                System.out.println("\033[0;33m" + this + " lost " + damage + " health! Remaining Health: " + healthPower + "\n");
            } else {
                System.out.println("\033[0;33m" + this + " didn't get hurt!");
            }
        } else {
            System.out.println("\033[0;36m" + this + " dodges the attack!\n");
        }
    }

    public boolean isDead(){
        return healthPower <= 0;
    }

    public void getSpellAttacked(Spell spell, int damage){
        damage -= defense * 0.05;
        healthPower -= damage;
        spell.deteriorate(this);
        System.out.println("\033[0;33m" + this + " lost " + damage + " health! Remaining Health: " + healthPower);
    }

    public void damageDeterioration(){
        damage = (int) (damage * 0.9);
        System.out.println("\033[0;33m" + this + "'s damage has deteriorated!");
    }

    public void defenseDeterioration(){
        defense = (int) (defense * 0.9);
        System.out.println("\033[0;33m" + this + "'s defense has deteriorated!");
    }

    public void dodgeDeterioration(){
        dodgeChance = (int) (dodgeChance * 0.9);
        System.out.println("\033[0;33m" + this + "'s dodgeChance has deteriorated!");
    }

}
