package Map;

public class NexusCellFactory extends FixedCellFactory {

	private boolean defaultAccessibility;
	
	public NexusCellFactory() {
		defaultAccessibility = true;
	}
	
	@Override
	public GameArea createContainedArea() {
		return new Nexus();
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
