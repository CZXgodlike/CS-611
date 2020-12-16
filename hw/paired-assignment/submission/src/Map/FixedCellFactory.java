package Map;

public abstract class FixedCellFactory extends CellFactory{
	
	public abstract GameArea createContainedArea();
	public abstract boolean getDefaultAccessibility();
	public abstract void setDefaultAccessibility(boolean defaultAccessibility);
	
	@Override
	public Cell createCell() {
		Cell c = new Cell();
		GameArea area = createContainedArea();
		boolean accessible = getDefaultAccessibility();
		c.setContainedArea(area);
		c.setAccessible(accessible);
		return c;
	}

}
