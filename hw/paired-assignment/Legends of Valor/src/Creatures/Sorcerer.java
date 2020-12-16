package Creatures;

import java.util.List;

/**
 * Class represents a sorcerer hero
 */

public class Sorcerer extends Hero{

    public Sorcerer(){
        super();
    }

    public Sorcerer(List<String> list){
        super(list);
    }

    @Override
    public void favoredSkill() {
        strength.setStat((int) (strength.getStat() * 1.05));
        dexterity.setStat((int) (dexterity.getStat() * 1.1));
        agility.setStat((int) (agility.getStat() * 1.1));
    }
}
