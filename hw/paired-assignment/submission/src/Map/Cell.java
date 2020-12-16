package Map;

import java.util.HashMap;

public class Cell {

	private boolean accessible;
	private GameArea containedArea;
	private int x, y;
	private boolean occupied;
	private String occupyingString;
	private HashMap<String,Cell>connectedCells;

	public Cell(){
		this(0,0);
    }

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
    	accessible = false;
    	occupied = false;
    	occupyingString = "";
    	connectedCells = new HashMap<String,Cell>();
	}

	public GameArea getContainedArea() {
		return containedArea;
	}

	public void setContainedArea(GameArea containedArea) {
		this.containedArea = containedArea;
	}
	
	public boolean getAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

    //Return true if hero can enter, false otherwise
    public boolean isAccessible() {
    	return accessible && !occupied;
    }

    //Return true if the cell has market, false otherwise
    public boolean hasMarket(){
    	return containedArea.isMarket();
    }
    
	//Space-drawing helper: Returns a text-representation of an inaccessible area
	protected MapGraphics drawUnenterablePattern(int height, int width) {
		MapGraphics design = new MapGraphics();
		for(int row = 0; row < height; row++) {
			StringBuilder rowSB = new StringBuilder();
			for(int col = 0; col < width; col++) {
				char additionalChar;
				if((col+row)%2 == 0) {
					additionalChar = '/';
				}
				else {
					additionalChar = ' ';
				}
				rowSB.append(additionalChar);
			}
			design.appendLine(rowSB);
		}
		return design;
	}
    
	//Draws a cell based on its contents/accessibility
    public MapGraphics drawCell(int cellHeight, int cellWidth) {
    	if(!accessible) {
    		return drawUnenterablePattern(cellHeight, cellWidth);
    	}
    	MapGraphics m = containedArea.drawAreaPattern(cellHeight, cellWidth);
    	insertOccupiersIntoPanel(m, cellHeight, cellWidth);
    	return m;
    }

	//Space-drawing helper: Edits a drawing to account for occupiers in the cell
	protected void insertOccupiersIntoPanel(MapGraphics panel, int height, int width) {
		if(occupied) {
			panel.overlay(occupyingString, (height/2), (width/2)-(occupyingString.length()/2));
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void removeOccupier() {
		occupyingString = "";
		occupied = false;
	}

	public void insertOccupier(String str) {
		occupyingString = str;
		occupied = true;
	}

	//Finds the Cell in the given direction
	public Cell getCellInDirection(String direction) {
		return connectedCells.get(direction);
	}
	
	//Creates a connection with another Cell
	public void addConnection(String direction, Cell cell) {
		connectedCells.put(direction, cell);
	}
	
	//Removes a connection with another Cell
	public void removeConnection(String direction) {
		connectedCells.remove(direction);
	}
    
}
