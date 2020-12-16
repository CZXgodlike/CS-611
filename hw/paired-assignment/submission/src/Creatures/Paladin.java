package Creatures;

import java.util.List;

/**
 * Class represents a paladin hero
 */

public class Paladin extends Hero{

    public Paladin(){
        super();
    }

    public Paladin(List<String> list){
        super(list);
    }

    @Override
    public void favoredSkill() {
        strength.setStat((int) (strength.getStat() * 1.1));
        dexterity.setStat((int) (dexterity.getStat() * 1.1));
        agility.setStat((int) (agility.getStat() * 1.05));
    }
}
