package Map;

public class Nexus extends GameArea {
	
	protected boolean market;
	protected char areaSymbol;
	
	public Nexus() {
		super();
		market = true;
		areaSymbol = '*';
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
