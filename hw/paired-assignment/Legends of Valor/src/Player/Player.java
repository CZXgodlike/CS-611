package Player;

import Helper.HeroFactory;
import Creatures.Hero;
import UserInterface.GameInterface;

/**
 * Class represents a player of Legends: Monster and Heroes
 */
public class Player {

    //private Hero[] heroes;
    private HeroTeam heroes;

    public Player(){}

    public Player(GameInterface gi){
        heroes = new HeroTeam(3);
        createHeroes(gi);
    }

    private void createHeroes(GameInterface gi){
        HeroFactory hf = new HeroFactory();
        for(int i = 0; i < 3; i++){
            String name;
            while(hasHero(name = gi.getHeroName(i))){
                System.out.println("\033[0;31m" + "You have already had this hero! Please select another one:");
            }
            heroes.getHeroes()[i] = hf.create(name);
        }
    }

    public boolean hasHero(String name){
        for(Hero hero: heroes.getHeroes()){
            if(hero == null){
            } else if(hero.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public Hero getHero(String name){
        for(Hero hero: heroes.getHeroes()){
            if(hero.getName().equalsIgnoreCase(name)){
                return hero;
            }
        }
        return null;
    }

    public Hero[] getHeroes() {
        return heroes.getHeroes();
    }

    /*public void printInventory(){
        for(Hero hero: heroes.getHeroes()){
            hero.getInventory().printInventory();
        }
    }*/

}
