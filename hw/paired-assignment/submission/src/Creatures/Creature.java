package Creatures;

import Stuffs.Tracker;

import java.util.List;

public abstract class Creature {

    protected String name;
    protected Tracker tracker;
    protected int level;

    public Creature() {}

    public Creature(List<String> list){
        name = list.get(0);
        tracker = new Tracker();
    }

    public String getName() {
        return name;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return name;
    }
}
