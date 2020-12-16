package Items;

import Creatures.Monster;

import java.util.List;

/**
 * Class represents a lightning spell
 */

public class LightningSpell extends Spell{

    public LightningSpell(){
        super();
    }

    public LightningSpell(List<String> list){
        super(list);
    }

    @Override
    public void deteriorate(Monster monster) {
        monster.dodgeDeterioration();
        System.out.println(monster + "'s dodge chance is deteriorated!");
    }
}
