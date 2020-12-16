package Map;

public class PlainCellFactory extends FixedCellFactory {

	private boolean defaultAccessibility;

	public PlainCellFactory() {
		defaultAccessibility = true;
	}
	
	@Override
	public GameArea createContainedArea() {
		return new PlainCell();
	}

	@Override
	public boolean getDefaultAccessibility() {
		return defaultAccessibility;
	}

	@Override
	public void setDefaultAccessibility(boolean defaultAccessibility) {
		this.defaultAccessibility = defaultAccessibility;
	}

}
