package Map;

public class CaveCellFactory extends FixedCellFactory {

	private boolean defaultAccessibility;
	
	public CaveCellFactory() {
		defaultAccessibility = true;
	}
	
	@Override
	public GameArea createContainedArea() {
		return new Cave();
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
