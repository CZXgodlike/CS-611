package Map;

import Helper.DataBase;
import Helper.MonsterFactory;
import Creatures.Hero;
import Creatures.Monster;
import Stuffs.Tracker;
import UserInterface.TextMessageDecorator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Map consists of three lanes: 1, 2 and 3
 */
public class LegendMap {
	
	private ArrayList<Monster>monsters;
	private ArrayList<Hero>heroes;
	private Cell[][] cells;
	private int height, width, laneWidth, gapWidth, drawnCellHeight, drawnCellWidth;
	private Lane[] lanes;
	private Random rng;
	
	public LegendMap() {
		this(8,8,2,1); //default settings
	}

    public LegendMap(int height, int width, int laneWidth, int gapWidth) {

		rng = new Random();

    	this.height = height;
    	this.width = width;
    	this.laneWidth = laneWidth;
    	this.gapWidth = gapWidth;
    	
    	//These are the values that keep the cells relatively square
    	drawnCellHeight = 4;
    	drawnCellWidth = 9;

    	//These are the probabilities for each cell type--they can be changed as long as they are positive and add to 1
    	double plainProb = 0.4;
    	double bushProb = 0.2;
    	double koulouProb = 0.2;
    	double caveProb = 0.2;

    	CellFactory factory = makeRandomCellFactory(plainProb, bushProb, koulouProb, caveProb);
    	
    	cells = new Cell[height][width];
    	for(int row = 0; row < height; row++) {
    		for(int column = 0; column < width; column++) {
    			Cell c = factory.createCell();
    			cells[row][column] = c;
    			c.setX(row);
    			c.setY(column);
    		}
    	}
    	determineCellConnections();
    	determineLanes();
    	monsters = new ArrayList<Monster>();
    	heroes = new ArrayList<Hero>();
    }
	
    //Checks how many non-divider columns the grid will have
	private int calculateNumberOfNonDividerColumns() {
		int counter = 0;
		for(int i = 0; i < width; i += laneWidth+gapWidth) {
			for(int j = 0; j < laneWidth && (i+j) < width; j++) {
				counter++;
			}
		}
		return counter;
	}
	
	//Sets the relation between adjacent cells
	protected void determineCellConnections() {
    	for(int row = 0; row < height; row++) {
    		for(int column = 0; column < width; column++) {
    			Cell current = cells[row][column];
    			if(row != 0) {
    				Cell c2 = cells[row-1][column];
    				current.addConnection("up", c2);
    			}
    			if(row != cells.length-1) {
    				Cell c2 = cells[row+1][column];
    				current.addConnection("down", c2);
    			}
    			if(column != 0) {
    				Cell c2 = cells[row][column-1];
    				current.addConnection("left", c2);
    			}
    			if(column != cells[row].length-1) {
    				Cell c2 = cells[row][column+1];
    				current.addConnection("right", c2);
    			}
    		}
    	}
	}

	//Returns a factory that generates cells to match the format of a Valor grid
	protected CellFactory makeCellFactoryForValorGrid(CellFactory nexusFactory, CellFactory commonFactory, CellFactory dividerFactory) {
		int nonNexusHeight = height-2, nonDividerWidth = calculateNumberOfNonDividerColumns();
		SequentialCellFactory sequentialFactory = new SequentialCellFactory();
		sequentialFactory.addFactory(nexusFactory, nonDividerWidth);
		sequentialFactory.addFactory(commonFactory,nonNexusHeight*nonDividerWidth);
		sequentialFactory.addFactory(nexusFactory, nonDividerWidth);
		int pos = laneWidth, counter = 0;
		while(pos < width) {
			sequentialFactory.addFactoryAtInterval(pos, nonDividerWidth+counter, dividerFactory);
			counter++;
			pos += gapWidth + laneWidth;
		}
		return sequentialFactory;
	}
	
	//Makes a RandomCellFactory given the probabilities
    private CellFactory makeRandomCellFactory(double plainProb, double bushProb, double koulouProb, double caveProb) {

		PlainCellFactory plainFactory = new PlainCellFactory();
		PlainCellFactory inaccessibleSpaceFactory = new PlainCellFactory();
		inaccessibleSpaceFactory.setDefaultAccessibility(false);
		NexusCellFactory nexusFactory = new NexusCellFactory();
		BushCellFactory bushFactory = new BushCellFactory();
		KoulouCellFactory koulouFactory = new KoulouCellFactory();
		CaveCellFactory caveFactory = new CaveCellFactory();
		RandomCellFactory randomFactory = new RandomCellFactory();

		randomFactory.setRNG(rng);
		randomFactory.addSpaceFactory(plainFactory, plainProb);
		randomFactory.addSpaceFactory(bushFactory, bushProb);
		randomFactory.addSpaceFactory(koulouFactory, koulouProb);
		randomFactory.addSpaceFactory(caveFactory, caveProb);

		return makeCellFactoryForValorGrid(nexusFactory, randomFactory, inaccessibleSpaceFactory);
    }
    
    //Creates a lane that starts at the given column
    private Lane createLaneAt(int startCol) {
    	Cell[][]laneCells = new Cell[height][laneWidth];
    	for(int row = 0; row < height; row++) {
    		for(int column = startCol, i = 0; column < width && column < startCol + laneWidth; column++, i++) {
    			laneCells[row][i] = cells[row][column];
    		}
    	}
    	return new Lane(laneCells);
    }
    
    //Divides the grid into lanes
    private void determineLanes() {
    	ArrayList<Lane>allLanes = new ArrayList<Lane>();
    	for(int column = 0; column < width; column += laneWidth + gapWidth) {
    		allLanes.add(createLaneAt(column));
    	}
    	lanes = allLanes.toArray(new Lane[0]);
    }

    //Return the cell at Cells[x][y]
    public Cell getCell(int x, int y) {
    	return cells[x][y];
    }
    
	//Draws the map
	public MapGraphics drawFullMap() {
		MapGraphics fullGraphics = new MapGraphics();
		for(Cell[] row : cells) {
			MapGraphics rowGraphics = new MapGraphics();
			for(Cell cell : row) {
				rowGraphics.appendPanelRight(cell.drawCell(drawnCellHeight, drawnCellWidth), 0, "|", 0);
			}
			rowGraphics.addRightWall("|");
			fullGraphics.appendPanelBelow(rowGraphics, 0, "-", 0);
		}
		fullGraphics.addFloor("-");
		return fullGraphics;
	}

    //print the map
    public void printMap() {
    	MapGraphics graphics = drawFullMap();
    	System.out.println("\033[0;34m" + graphics.toString());
    }

    //Gets the specified lane
    public Lane getLane(int laneNum) {
    	return lanes[laneNum];
    }

    //Initialize heroes' position when game starts
    public void spawnHeroes(Hero[] heroes) {
    	for(int i = 0; i < heroes.length; i++) {
    		Hero h = heroes[i];
    		Tracker t = h.getTracker();
    		Cell[][] laneCells = lanes[i].getCells();
    		Cell spawnPoint = laneCells[laneCells.length-1][0];
    		t.setLabel("H" + (i+1));
    		t.moveToCell(spawnPoint);
			this.heroes.add(h);
    	}
    }

	private int getLowestLevel(Hero[] heroes){
		int minLevel = 10;
		for(Hero hero: heroes){
			if(hero.getLevel() < minLevel){
				minLevel = hero.getLevel();
			}
		}
		return minLevel;
	}
    
    //Generates a random monster
    public Monster randomMonster(Hero[] heroes) {
		MonsterFactory monsterFactory = new MonsterFactory();
		DataBase dataBase = new DataBase();
		return monsterFactory.createMonsters(dataBase.getMonsters(getLowestLevel(heroes)), 1)[0];
    }

    //Randomly create monsters with the highest level of heroes
    public void spawnMonsters(Hero[] heroes){
		for (int i = 0; i < 3; i++) {
			Lane lane = lanes[i];
			Monster m = randomMonster(heroes);
			Tracker t = m.getTracker();
			Cell[][] laneCells = lane.getCells();
			Cell spawnPoint = laneCells[0][0];
			t.setLabel("M");
			t.moveToCell(spawnPoint);
			monsters.add(m);
			System.out.println("\033[0;31m" + m + " is born in lane " + (i + 1));
		}
    }

    //Return all the monsters in the map
    public Monster[] getMonsters() {
    	return monsters.toArray(new Monster[0]);
    }

    //Return "hero" if hero wins (or a tie), "monster" if monster wins, otherwise return null
	public String checkWinner() {
		for(Hero h : heroes) {
			if(h.getTracker().getCurrentCell().getX() == 0) {
				return "hero";
			}
		}
		for(Monster m : monsters) {
			if(m.getTracker().getCurrentCell().getX() == height-1) {
				return "monster";
			}
		}
		return null;
	}

	//Check monsters' HP and remove dead monsters from map
	public void updateMonsters() {
		ArrayList<Monster>toRemove = new ArrayList<Monster>();
		for(Monster m : monsters) {
			if(m.isDead()) {
				toRemove.add(m);
			}
		}
		for(Monster m : toRemove) {
			monsters.remove(m);
			m.getTracker().moveToCell(null);
		}
	}

	//Reborn hero who are faint at nexus. laneNum represents the original lane number of the hero.
	public void reborn(Hero hero, int laneNum) {
		TextMessageDecorator tmd = new TextMessageDecorator();
		System.out.println(tmd.addColor("red", hero + " is faint!"));
		Lane lane = lanes[laneNum];
		if(lane.canTeleport()) {
			lane.teleport(hero);
			System.out.println(tmd.addColor("red",
					hero + " is reborn on lane " + laneNum));
		}
		else if(laneNum + 1 <= 2){
			System.out.println(tmd.addColor("red",
					"Nexus is occupied. " + hero + " is reborn on lane " + (laneNum + 1)));
			lane = lanes[laneNum + 1];
			lane.teleport(hero);
		} else {
			System.out.println(tmd.addColor("red",
					"Nexus is occupied. " + hero + " is reborn on lane " + (laneNum - 1)));
			lane = lanes[laneNum - 1];
			lane.teleport(hero);
		}
		hero.revive();
	}
}
