package Map;

public class Bush extends GameArea {
	
	protected boolean market;
	protected char areaSymbol;

	public Bush() {
		super();
		market = false;
		areaSymbol = '&';
	}

	@Override
	public boolean isMarket() {
		return market;
	}
	
	@Override
	public char getAreaSymbol() {
		return areaSymbol;
	}

}
