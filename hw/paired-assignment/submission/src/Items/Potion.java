package Items;

import Creatures.Hero;

import java.util.List;

/**
 * Class represents a potion
 */

public class Potion extends Item{

    private final int attribute_increase;
    private final String attribute_affected;

    public Potion(List<String> list){
        super(list);
        attribute_increase = Integer.parseInt(list.get(3));
        attribute_affected = list.get(4);
    }

    public void use(Hero hero){
        if(attribute_affected.contains("Health")){
            hero.attribute_increase("H", attribute_increase);
        }
        if(attribute_affected.contains("Mana")){
            hero.attribute_increase("M", attribute_increase);
        }
        if(attribute_affected.contains("Stength")){
            hero.attribute_increase("S", attribute_increase);
        }
        if(attribute_affected.contains("Dexterity")){
            hero.attribute_increase("D", attribute_increase);
        }
        if(attribute_affected.contains("Agility")){
            hero.attribute_increase("A", attribute_increase);
        }

        hero.getInventory().remove(name);
    }
}
