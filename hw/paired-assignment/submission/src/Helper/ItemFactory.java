package Helper;

import Items.*;

import java.util.List;

/**
 * Class representing an item factory.
 * Create and return an item according to given item's name
 */

public class ItemFactory extends Factory {

    public ItemFactory(){super();}

    public Item create(String name){
        List<String> list = dataBase.inquire(name).get(1);
        String category = list.get(list.size() - 1);
        return switch (category) {
            case "Armor" -> new Armor(list);
            case "Fire_Spell" -> new FireSpell(list);
            case "Ice_Spell" -> new IceSpell(list);
            case "Lightning_Spell" -> new LightningSpell(list);
            case "Potion" -> new Potion(list);
            case "Weapon" -> new Weapon(list);
            default -> null;
        };
    }
}
