package Map;
import java.util.ArrayList;

public class SequentialCellFactory extends CellFactory {

	private ArrayList<CellFactory>cellFactories;
	private int counter;

	public SequentialCellFactory() {
		cellFactories = new ArrayList<CellFactory>();
		counter = 0;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void resetCounter() {
		counter = 0;
	}
	
	protected CellFactory selectSpaceFactory(int num) {
		return cellFactories.get(num%cellFactories.size());
	}
	
	public void addFactory(CellFactory f) {
		addFactory(f, 1);
	}

	public void addFactoryAt(int pos, CellFactory f) {
		addFactoryAt(pos, f, 1);
	}

	public void addFactory(CellFactory f, int count) {
		for(int i = 0; i < count; i++) {
			cellFactories.add(f);
		}
	}

	public void addFactoryAt(int pos, CellFactory f, int count) {
		for(int i = 0; i < count; i++) {
			cellFactories.add(pos, f);
		}
	}
	
	public void addFactoryAtInterval(int offset, int interval, CellFactory f) {
		int nextLocation = offset;
		for(int i = offset; i < cellFactories.size(); i++) {
			if(nextLocation == i) {
				addFactoryAt(i, f);
				nextLocation = i+interval+1;
			}
		}
	}

	public CellFactory removeFactoryAt(int pos) {
		return cellFactories.remove(pos);
	}
	
	@Override
	public Cell createCell() {
		CellFactory f = selectSpaceFactory(counter);
		counter++;
		return f.createCell();
	}

}
