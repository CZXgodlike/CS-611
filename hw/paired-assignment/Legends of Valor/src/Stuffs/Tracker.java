package Stuffs;

import Map.Cell;

public class Tracker {

    private int x;
    private int y;
    private Cell currentCell;
    private String label;


	public Tracker(){
    	setPosition(0,0);
    	currentCell = null;
    }

    public Cell getCurrentCell() {
		return currentCell;
	}


	public void setCurrentCell(Cell currentCell) {
		this.currentCell = currentCell;
	}

    //Set position to (x,y)
    public void setPosition(int x, int y){
	    this.x = x;
	    this.y = y;
    }

    public boolean nextTo(Tracker tracker){
        return Math.abs(x - tracker.getX()) <= 1 && Math.abs(y - tracker.getY()) <= 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void moveToCell(Cell targetCell) {
    	if(currentCell!=null) {
			currentCell.removeOccupier();
    	}
    	if(targetCell!=null) {
			targetCell.insertOccupier(label);
			setPosition(targetCell.getX(), targetCell.getY());
    	}
    	currentCell = targetCell;
    }
    
    private void moveInDirection(String direction) {
    	Cell c = currentCell.getCellInDirection(direction);
    	moveToCell(c);
    }

    //Make according change to x and y
    public void moveDown(){
    	moveInDirection("down");
    }

    public void moveUp(){
    	moveInDirection("up");
    }

    public void moveLeft(){
    	moveInDirection("left");
    }

    public void moveRight(){
    	moveInDirection("right");
    }

    public boolean canMoveTo(String direction){
        Cell c = currentCell.getCellInDirection(direction);
        return c != null && c.isAccessible();
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getLaneNum(){
	    if(y == 0 || y == 1){
	        return 0;
        } else if(y == 3 || y == 4){
	        return 1;
        } else{
	        return 2;
        }
    }
}
