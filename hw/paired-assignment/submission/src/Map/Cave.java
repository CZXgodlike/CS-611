package Map;

public class Cave extends GameArea {
	
	protected boolean market;
	protected char areaSymbol;
	
	public Cave() {
		super();
		market = false;
		areaSymbol = 'W';
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
