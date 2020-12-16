package Map;

public class KoulouCellFactory extends FixedCellFactory {

	private boolean defaultAccessibility;
	
	public KoulouCellFactory() {
		defaultAccessibility = true;
	}
	
	@Override
	public GameArea createContainedArea() {
		return new Koulou();
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
