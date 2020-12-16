package Map;

public class Koulou extends GameArea {
	
	protected boolean market;
	protected char areaSymbol;
	
	public Koulou() {
		super();
		market = false;
		areaSymbol = '%';
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
