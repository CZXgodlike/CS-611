package Map;

public class PlainCell extends GameArea {
	
	protected boolean market;
	protected char areaSymbol;
	
	public PlainCell() {
		super();
		market = false;
		areaSymbol = ' ';
	}

	@Override
	public boolean isMarket() {
		return market;
	}

	@Override
	protected char getAreaSymbol() {
		return areaSymbol;
	}

}
