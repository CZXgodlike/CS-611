package Helper;

import Creatures.Dragon;
import Creatures.Exoskeleton;
import Creatures.Monster;
import Creatures.Spirit;

import java.util.List;
import java.util.Random;

/**
 * Class representing a monster factory.
 * Create and return a monster according to given monster's name
 */

public class MonsterFactory extends Factory {

    public MonsterFactory(){
        super();
    }

    @Override
    public Monster create(String name) {
        List<String> list = dataBase.inquire(name).get(1);
        String category = list.get(list.size() - 1);
        return switch (category) {
            case "Dragon" -> new Dragon(list);
            case "Exoskeleton" -> new Exoskeleton(list);
            case "Spirit" -> new Spirit(list);
            default -> null;
        };
    }

    public Monster[] createMonsters(List<List<String>> list, int number){
        Monster[] monsters = new Monster[number];
        for(int i = 0; i < number; i++){
            int rnd = new Random().nextInt(list.size());
            monsters[i] = create(list.get(rnd).get(0));
        }
        return monsters;
    }
}
