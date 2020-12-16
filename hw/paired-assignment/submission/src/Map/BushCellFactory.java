package Map;

public class BushCellFactory extends FixedCellFactory {
	
	private boolean defaultAccessibility;
	
	public BushCellFactory() {
		defaultAccessibility = true;
	}

	@Override
	public GameArea createContainedArea() {
		return new Bush();
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
