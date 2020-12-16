package UserInterface;

import Creatures.Hero;
import Map.Cell;
import Map.LegendMap;
import Stuffs.Tracker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller extends GameIO{

    private Hero hero;
    private MyScanner scanner;
    private LegendMap map;
    private Tracker tracker;
    private int lane;
    protected String red = "\033[0;31m";

    public Controller(){}

    public Controller(Hero hero, LegendMap map, int lane){
        scanner = new MyScanner(hero);
        this.hero = hero;
        this.map = map;
        this.tracker = hero.getTracker();
        setOriginLocation(lane);
    }

    public int getLane() {
        return lane;
    }

    public void setOriginLocation(int lane){
        if(lane == 0){
            tracker.setPosition(7, 0);
        } else if(lane == 1){
            tracker.setPosition(7, 3);
        } else if(lane == 2){
            tracker.setPosition(7, 6);
        }
        this.lane = lane;
    }

    public void getNextMove(){
        String s;
        System.out.println(tmd.addColor("original", "Please enter your move for " + hero + ":"));

        while(true){
            try{
                s = scanner.nextLine();
                if(s.equalsIgnoreCase("a")){
                    if(tracker.getY() == 0 || !map.getCell(tracker.getX(), tracker.getY()-1).isAccessible()){
                        System.out.println(red + "Cannot move left.");
                    } else {
                        hero.leave(tracker.getCurrentCell());
                        tracker.moveLeft();
                        hero.boost(tracker.getCurrentCell());
                        break;
                    }
                } else if(s.equalsIgnoreCase("d")){
                    if(tracker.getY() == 7 || !map.getCell(tracker.getX(), tracker.getY()+1).isAccessible()){
                        System.out.println(red + "Cannot move right.");
                    } else {
                        hero.leave(tracker.getCurrentCell());
                        tracker.moveRight();
                        hero.boost(tracker.getCurrentCell());
                        break;
                    }
                } else if(s.equalsIgnoreCase("w")){
                    if(tracker.getX() == 0 || !map.getCell(tracker.getX()-1, tracker.getY()).isAccessible()){
                        System.out.println(red + "Cannot move up.");
                    } else {
                        hero.leave(tracker.getCurrentCell());
                        tracker.moveUp();
                        hero.boost(tracker.getCurrentCell());
                        break;
                    }
                } else if(s.equalsIgnoreCase("s")){
                    if(tracker.getX() == 7 || !map.getCell(tracker.getX()+1, tracker.getY()).isAccessible()){
                        System.out.println(red + "Cannot move down");
                    } else {
                        hero.leave(tracker.getCurrentCell());
                        tracker.moveDown();
                        hero.boost(tracker.getCurrentCell());
                        break;
                    }
                } else if(s.equalsIgnoreCase("m")){
                    map.printMap();
                    System.out.println("\033[0;36m" + "Please continue your move:");
                } else if(s.equalsIgnoreCase("b")){
                    if(map.getLane(lane).canTeleport()){
                        map.getLane(lane).teleport(hero);
                        break;
                    } else {
                        System.out.println(tmd.addColor("red", "Cannot go back to nexus. Please try other moves:"));
                    }
                } else if(s.equalsIgnoreCase("t")){
                    if(tracker.getLaneNum() != lane){
                        System.out.println(tmd.addColor("red", "Cannot teleport from this lane. You can press b to go back to your own nexus first."));
                    }
                    else {
                        teleport();
                        break;
                    }
                }else{
                    System.out.println(red + "Please enter w/s/a/d to move:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again!");
            }
        }
        //map.printMap();
    }

    public void teleport(){
        System.out.println(tmd.addColor("original", "Please enter the lane you want to move to (0/1/2):"));
        Scanner sc = new Scanner(System.in);
        int laneNum;

        while(true){
            try {
                laneNum = sc.nextInt();
                if(laneNum == lane){
                    System.out.println(tmd.addColor("red", "You are already on this lane!"));
                } else if(laneNum < 0 || laneNum > 3){
                    System.out.println(tmd.addColor("red", "Invalid input. Please enter a number between 1 and 3:"));
                }else if(!map.getLane(laneNum).canTeleport()){
                    System.out.println(tmd.addColor("red", "No available nexus cell on this lane, please select another lane."));
                } else {
                    map.getLane(laneNum).teleport(hero);
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println(tmd.addColor("red", "Invalid input. Please enter a number between 1 and 3:"));
                scanner.next();
            }
        }
    }

    public Cell getCurrentCell(){
        return map.getCell(tracker.getX(), tracker.getY());
    }

    public Hero getHero() {
        return hero;
    }

    /*protected MyScanner scanner;
    protected int x;
    protected int y;
    protected LegendsOfValorMap map;
    protected String red = "\033[0;31m";

    public Controller(){}

    public void getPlayerPosition(){
        for(int i = 0; i < map.getLength(); i++){
            for (int j = 0; j < map.getWidth(); j++){
                if(map.getCell(i, j).hasPlayer()){
                    x = i;
                    y = j;
                    return;
                }
            }
        }
    }

    public void getNextMove(){
        String s;
        Cell currentCell = map.getCell(x, y);
        Cell nextCell;

        while(true){
            try{
                s = scanner.nextLine();
                if(s.equalsIgnoreCase("a")){
                    if(y == 0 || !map.getCell(x, y-1).canEnter()){
                        System.out.println(red + "Cannot move left.");
                    } else {
                        nextCell = map.getCell(x, y - 1);
                        y = y - 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("d")){
                    if(y == map.getWidth() - 1 || !map.getCell(x, y+1).canEnter()){
                        System.out.println(red + "Cannot move right.");
                    } else {
                        nextCell = map.getCell(x, y+1);
                        y = y + 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("w")){
                    if(x == 0 || !map.getCell(x-1, y).canEnter()){
                        System.out.println(red + "Cannot move up.");
                    } else {
                        nextCell = map.getCell(x-1, y);
                        x -= 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("s")){
                    if(x == map.getLength() || !map.getCell(x+1, y).canEnter()){
                        System.out.println(red + "Cannot move down");
                    } else {
                        nextCell = map.getCell(x+1, y);
                        x += 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("m")){
                    map.printGrids();
                    System.out.println("\033[0;36m" + "Please continue your move:");
                } else{
                    System.out.println(red + "Please enter w/s/a/d to move:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again!");
            }
        }
        nextCell.playerEntered();
        currentCell.playerLeft();
        map.printGrids();
    }

    public Cell getCurrentCell(){
        return map.getCell(x, y);
    }*/
}
