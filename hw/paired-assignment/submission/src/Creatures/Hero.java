package Creatures;

import Items.Armor;
import Items.Item;
import Items.Spell;
import Items.Weapon;
import Map.Cell;
import Stuffs.Inventory;
import Stuffs.Tracker;
import Stuffs.Wallet;
import UserInterface.GameInterface;
import UserInterface.Graphic;
import UserInterface.TextMessageDecorator;

import java.util.ArrayList;
import java.util.List;

/**
 * abstract class representing a hero
 * Contains most of the function of hero's actions
 */

public abstract class Hero extends Creature{


    protected NumericAttribute healthPower = new NumericAttribute("Health Power");
    protected NumericAttribute mana = new NumericAttribute("Mana");
    protected NumericAttribute strength = new NumericAttribute("Strength");
    protected NumericAttribute dexterity = new NumericAttribute("Dexterity");
    protected NumericAttribute agility = new NumericAttribute("Agility");
    protected NumericAttribute exp = new NumericAttribute("Exp");
    protected Wallet wallet;
    protected Hands hands = new Hands();
    protected List<Spell> spells = new ArrayList<>();
    protected Armor armor = null;
    protected Inventory inventory = new Inventory(this);
    protected int boost;

    public Hero(List<String> list){
        super(list);
        this.level = 1;
        this.healthPower.setStat(level * 100);
        this.healthPower.setMaxStat(level * 100);
        this.mana.setStat(Integer.parseInt(list.get(1)));
        this.mana.setMaxStat(mana.getStat());
        this.strength.setStat(Integer.parseInt(list.get(2)));
        this.agility.setStat(Integer.parseInt(list.get(3)));
        this.dexterity.setStat(Integer.parseInt(list.get(4)));
        this.wallet = new Wallet(Integer.parseInt(list.get(5)));
        this.exp.setStat(Integer.parseInt(list.get(6)));
        this.exp.setMaxStat(level * 10);
        boost = 0;
    }

    public Hero() {
    }

    public int getLevel() {
        return level;
    }

    public void attack(String type, Monster monster, GameInterface gi){
        if(type.equalsIgnoreCase("normal") || type.equals(" ")){
            normalAttack(monster);
        } else {
            spellAttack(monster, gi.getSpell(this));
        }
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void normalAttack(Monster monster){
        int damage = 0;
        damage += hands.getDamage();
        damage += strength.getStat();
        System.out.println("\033[0;34m" + this + " makes a normal attack on " + monster);
        monster.getAttacked((int) (damage * 0.05));
    }

    public void spellAttack(Monster monster, Spell spell){
        int damage = (int) (spell.getDamage() * (1 + dexterity.getStat()/10000) * 0.05);
        System.out.println("\033[0;34m" + this + " uses " + spell + " on " + monster + "!");
        monster.getSpellAttacked(spell, damage);
    }// mana

    public boolean canUse(String name){
        for(Spell spell: spells){
            if(spell.getName().equalsIgnoreCase(name)){
                return spell.getMana_cost() <= mana.getStat();
            }
        }
        return false;
    }

    public boolean hasSpell(String name){
        for(Spell spell: spells){
            if(spell.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean hasAvailableSpell(){
        for(Spell spell: spells){
            if(mana.getStat() >= spell.getMana_cost()){
                return true;
            }
        }
        return false;
    }

    public void getAttacked(int damage){
        double i = Math.random();
        if (i <= agility.getStat() * 0.001){
            System.out.println("\033[0;36m" + this + " dodges the attack!" + ".\n");
        } else {
            if(armor != null){
                damage = damage * (armor.getDamage_reduction() / 3000);
            }
            System.out.println("\033[0;33m" + this + " lost " + damage + " health!");
            healthPower.decrease(damage, this);
        }
    }

    public void putOnArmor(Armor armor){
        if(this.armor != null){
          takeOffArmor();
        }
        this.armor = armor;
        System.out.println(this + " has put on " + armor);
        inventory.remove(armor.getName());
    }

    public void takeOffArmor(){
        inventory.add(armor);
        armor = null;
    }

    public void addSpell(Spell spell){
        spells.add(spell);
    }

    public void buy(Item item){
        if(item.isBuyable(wallet.getBalance(), level)){
            wallet.pay(item.getPrice());
            inventory.add(item);
            System.out.println("\033[0;32m" + this + " successfully bought " + item + ". "+ wallet);
        }
    }

    public boolean canBuy(Item item) {
        return item.isBuyable(wallet.getBalance(), level);
    }

    public void attribute_increase(String name, int amount){
        switch (name) {
            case "H" -> healthPower.increase(amount, this);
            case "M" -> mana.increase(amount, this);
            case "S" -> strength.increase(amount, this);
            case "A" -> agility.increase(amount, this);
            case "D" -> dexterity.increase(amount, this);
        }
    }

    public void levelUp(){
        while(exp.getStat() >= 10 * level){
            Graphic g = new Graphic();
            System.out.println(g.getLevelUp());
            exp.setStat(exp.getStat() - 10 * level);

            level += 1;
            System.out.println(this + " has upgraded to level " + level);
            healthPower.setMaxStat(100 * level);
            mana.setMaxStat((int) (mana.getMaxStat() * 1.1));
            healthPower.setStat(healthPower.getStat() + 100);
            mana.setStat((int) (mana.getStat() * 1.1));
            exp.setMaxStat(10 * level);
            favoredSkill();
        }
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void favoredSkill(){}

    public void printStat(){
        System.out.println("\033[0;33m" + this + "'s stats:" + "   Lv." + level);
        System.out.println("-".repeat(50));
        System.out.println();
        healthPower.printStat();
        mana.printStat();
        strength.printStat();
        dexterity.printStat();
        agility.printStat();
        exp.printStat();
        System.out.println(wallet);
        System.out.print("Spells: ");
        for(Spell spell: spells){
            System.out.print(spell + ", ");
        }
        System.out.println("\narmor: " + armor);
        System.out.print("weapon: ");
        for(Hand hand: hands.getHands()){
            if(hand.getWeapon()!=null){
                System.out.print(hand.getWeapon() + ", ");
            }
        }
        System.out.println("\n");
    }

    public void UseWeapon(Weapon weapon){
        hands.equipWeapon(weapon);
    }

    public boolean isFaint(){
        return healthPower.getStat() <= 0;
    }

    public Hands getHands() {
        return hands;
    }

    public void getReward(){
        exp.increase(2, this);
        wallet.earn(level * 100);
        levelUp();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void regain(){
        if(mana.getStat() == mana.getMaxStat()){}
        else {
            mana.setStat((int) (mana.getStat() * 1.1));
            System.out.println("\033[0;32m" + this + "'s mana has recovered to " + mana.getStat() + ".");
        }
    }

    public void boost(Cell cell){
        String cellType = cell.getContainedArea().getClass().getSimpleName();
        NumericAttribute attribute = null;
        if(cellType.equalsIgnoreCase("bush")){
            attribute = dexterity;
        } else if(cellType.equalsIgnoreCase("cave")){
            attribute = agility;
        } else if(cellType.equalsIgnoreCase("koulou")){
            attribute = strength;
        }
        if(attribute != null){
            boost = (int) (attribute.getStat() * 0.1);
            attribute.setStat(attribute.getStat() + boost);
            System.out.println("\033[0;32m" + this + "'s " + attribute + " has boosted by 10%!");
        }
    }

    public void leave(Cell cell){
        String cellType = cell.getContainedArea().getClass().getSimpleName();
        NumericAttribute attribute = null;
        if(cellType.equalsIgnoreCase("bush")){
            attribute = dexterity;
        } else if(cellType.equalsIgnoreCase("cave")){
            attribute = agility;
        } else if(cellType.equalsIgnoreCase("koulou")){
            attribute = strength;
        }
        if(attribute != null){
            attribute.setStat(attribute.getStat() - boost);
            System.out.println("\033[0;32m" + attribute + "'s buff has disappeared.");
        }
        boost = 0;
    }

    
    public void revive() {
        TextMessageDecorator tmd = new TextMessageDecorator();
        System.out.println(tmd.addColor("red", "Please notice that " + this + " has low HP now."));
        healthPower.setStat((int) (healthPower.getMaxStat()*0.1));
    }

}
