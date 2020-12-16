package Map;

import Creatures.Hero;
import Stuffs.Tracker;

public class Lane {
    private Cell[][] cells;

	public Lane(){
    	this(null);
    }
    
    public Lane(Cell[][]cells) {
    	this.cells = cells;
    }

    public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public boolean canTeleport(){
		return firstAvailableNexus() != null;
	}

	private Cell firstAvailableNexus(){
		Cell targetCell = null;
		for(int i = 0; i < cells[cells.length-1].length; i++) {
			Cell tempCell = cells[cells.length-1][i];
			if(!tempCell.isOccupied()) { //Found a free nexus point
				targetCell = tempCell;
				break;
			}
		}
		return targetCell;
	}

    // Execute when a hero teleport to this lane.
    // Add teleported hero to heroes and put it at the first available cell of nexus
    public void teleport(Hero hero) {
		Cell targetCell = firstAvailableNexus();
    	Tracker t = hero.getTracker();
    	t.moveToCell(targetCell);
    }
}
