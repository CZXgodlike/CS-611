package Game;

import Creatures.Hero;
import Map.LegendMap;
import Creatures.Monster;
import Player.Player;
import UserInterface.Controller;
import UserInterface.GameInterface;
import UserInterface.MarketInterface;
import UserInterface.TextMessageDecorator;

public class LegendsOfValor extends RPG{

    private LegendMap map;
    private GameInterface ui;
    private Player player;
    private Controller[] controllers;
    private int round;
    private TextMessageDecorator tmd;

    public LegendsOfValor(){
        ui = new GameInterface();
        ui.printBanner();
        map = new LegendMap();
        player = new Player(ui);
        initializeMap();
        controllers = new Controller[3];
        controllers[0] = new Controller(player.getHeroes()[0], map, 0);
        controllers[1] = new Controller(player.getHeroes()[1], map, 1);
        controllers[2] = new Controller(player.getHeroes()[2], map, 2);
        round = 0;
        tmd = new TextMessageDecorator();
    }

    public void initializeMap(){
        map.spawnHeroes(player.getHeroes());
        map.spawnMonsters(player.getHeroes());
    }

    public LegendMap getMap() {
        return map;
    }

    public boolean hasHeroesAround(Monster monster){
        for(Hero hero: player.getHeroes()){
            if(monster.getTracker().nextTo(hero.getTracker())){
                return true;
            }
        }
        return false;
    }

    public boolean hasMonstersAround(Hero hero){
        for(Monster monster: map.getMonsters()){
            if(monster.getTracker().nextTo(hero.getTracker())){
                return true;
            }
        }
        return false;
    }

    private void monsterRound(){
        System.out.println(tmd.addColor("original", "-".repeat(50)));
        System.out.println("Monsters' turn:");
        for(Monster monster: map.getMonsters()){
            if(hasHeroesAround(monster)){
                for(Hero hero: player.getHeroes()){
                    if(monster.getTracker().nextTo(hero.getTracker())){
                        monster.attack(hero);
                    }
                }
            } else if(monster.getTracker().canMoveTo("down")){
                monster.getTracker().moveDown();
            } else if(monster.getTracker().canMoveTo("right")){
                monster.getTracker().moveRight();
            } else if(monster.getTracker().canMoveTo("left")){
                monster.getTracker().moveLeft();
            }
        }
    }

    private boolean hasWinner(){
        String winner;
        winner = map.checkWinner();
        return winner != null;
    }

    public void reward(){
        for(Monster monster: map.getMonsters()){
            if(monster.isDead()){
                for(Hero hero: player.getHeroes()){
                    if(hero.getTracker().nextTo(monster.getTracker())){
                        hero.getReward();
                    }
                }
            }
        }
    }
    public void updateCharacters(){
        for(int i = 0; i < 3; i++){
            Hero hero = player.getHeroes()[i];
            if(hero.isFaint()){
                map.reborn(hero, controllers[i].getLane());
            }
        }
        map.updateMonsters();
    }

    @Override
    public void start() {
        String winner = null;
        while(winner == null){
            for(Controller controller: controllers){

                Hero hero = controller.getHero();
                System.out.println(tmd.addColor("original", "-".repeat(50)));
                System.out.println(hero + "'s turn:");


                if(controller.getCurrentCell().hasMarket()){
                    MarketInterface mi = new MarketInterface(hero);
                }
                map.printMap();
                if(hasMonstersAround(hero)){
                    System.out.println(tmd.addColor("original", hero + " needs to beat the monsters before moving forward!"));
                    hero.printStat();
                    for(Monster monster: map.getMonsters()){
                        if(monster.getTracker().nextTo(hero.getTracker())){
                            hero.attack(ui.getAttackType(hero), monster, ui);
                        }
                    }
                    hero.regain();
                } else {
                    controller.getNextMove();
                }
                reward();
                updateCharacters();

                if(hasWinner()){
                    winner = map.checkWinner();
                    break;
                }
            }

            if(winner != null){
                break;
            }
            //Monsters move
            monsterRound();
            if(round % 8 == 1 && round != 1){
                map.spawnMonsters(player.getHeroes());
            }
            round ++;
        }
        if(winner.equalsIgnoreCase("hero")){
            ui.printCastle();
        } else {
            ui.printSkeleton();
        }
    }
    /*public void start(){
        map.printGrids();
        while(true){
            controller.getNextMove();
            if(!controller.getCurrentCell().hasMarket() && meetMonsters()){
                FreeForAll freeForAll = new FreeForAll(player.getHeroes());
                freeForAll.start();
                map.printGrids();
            } else if(controller.getCurrentCell().hasMarket()){
                MarketInterface mi = new MarketInterface(this);
                map.printGrids();
                System.out.println("\033[0;35m" + "Please continue your move:");
            }
        }
    }*/

}
