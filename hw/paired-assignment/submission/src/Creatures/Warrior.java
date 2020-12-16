package Creatures;

import java.util.List;

/**
 * Class represents a warrior hero
 */

public class Warrior extends Hero{

    public Warrior(){
        super();
    }

    public Warrior(List<String> list){
        super(list);
    }

    @Override
    public void favoredSkill() {
        strength.setStat((int) (strength.getStat() * 1.1));
        dexterity.setStat((int) (dexterity.getStat() * 1.05));
        agility.setStat((int) (agility.getStat() * 1.1));
    }
}
