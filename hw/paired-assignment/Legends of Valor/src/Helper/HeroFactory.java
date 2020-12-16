package Helper;

import Creatures.Hero;
import Creatures.Paladin;
import Creatures.Sorcerer;
import Creatures.Warrior;

import java.util.List;

/**
 * Class representing a hero factory.
 * Create and return a hero according to given hero's name
 */

public class HeroFactory extends Factory {

    public HeroFactory(){
        super();
    }

    public Hero create(String name){
        List<String> list = dataBase.inquire(name).get(1);
        String category = list.get(list.size() - 1);
        return switch (category) {
            case "Warrior" -> new Warrior(list);
            case "Sorcerer" -> new Sorcerer(list);
            case "Paladin" -> new Paladin(list);
            default -> null;
        };
    }

}
