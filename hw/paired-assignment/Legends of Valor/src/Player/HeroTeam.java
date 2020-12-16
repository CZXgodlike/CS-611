package Player;

import Creatures.Hero;

public class HeroTeam {

    private Hero[] heroes;

    public HeroTeam(){}

    public HeroTeam(int number){
        heroes = new Hero[number];
    }

    public Hero[] getHeroes() {
        return heroes;
    }
}
