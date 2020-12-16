package Map;

public abstract class GameArea {
	public abstract boolean isMarket();
	protected abstract char getAreaSymbol();

	public MapGraphics drawAreaPattern(int height, int width) {
		MapGraphics design = new MapGraphics();
		design.appendLine("");
		design.padToHeight(height);
		design.padToEdge(width);
		design.specifyCorners(getAreaSymbol());
		return design;
	}
	
}
