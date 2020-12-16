package Items;

import Creatures.Hero;
import Creatures.Monster;

import java.util.List;

/**
 * Abstract class represents spell
 * A spell can be a fire spell, an ice spell or a lightning spell
 */

public abstract class Spell extends Item {

    protected int mana_cost;
    protected int damage;

    public Spell(){super();};

    public Spell(List<String> list){
        super(list);
        damage = Integer.parseInt(list.get(3));
        mana_cost = Integer.parseInt(list.get(4));
    }

    public int getMana_cost() {
        return mana_cost;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void deteriorate(Monster monster){}

    public void use(Hero hero){
        hero.getInventory().remove(name);
        hero.addSpell(this);
    }
}
