package Items;

import Creatures.Monster;

import java.util.List;

/**
 * Class represents a fire spell
 */

public class FireSpell extends Spell {

    public FireSpell(){
        super();
    }

    public FireSpell(List<String> list){
        super(list);
    }

    public void deteriorate(Monster monster){
        monster.defenseDeterioration();
    }
}
