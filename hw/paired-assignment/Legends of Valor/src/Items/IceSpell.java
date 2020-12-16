package Items;

import Creatures.Monster;

import java.util.List;

/**
 * Class represents an ice spell
 */

public class IceSpell extends Spell{

    public IceSpell(){
        super();
    }

    public IceSpell(List<String> list){
        super(list);
    }

    public void deteriorate(Monster monster){
        monster.damageDeterioration();
    }
}
