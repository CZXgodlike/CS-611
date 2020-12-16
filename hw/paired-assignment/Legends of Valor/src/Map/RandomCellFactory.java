package Map;
import java.util.ArrayList;
import java.util.Random;

//Provides a factory that randomly calls on subfactories to create GameSpaces
public class RandomCellFactory extends CellFactory {
	
	private ArrayList<CellFactory>cellFactories;
	private ArrayList<Double>probabilities;
	private double currentProbabilityTotal;
	private Random rng;
	
	public RandomCellFactory() {
		cellFactories = new ArrayList<CellFactory>();
		probabilities = new ArrayList<Double>();
		currentProbabilityTotal = 0.0;
		rng = null;
	}
	
	//Adds a factory that will be called with a probability of p
	public boolean addSpaceFactory(CellFactory factory, double p) {
		if(cellFactories.contains(factory)) {
			return false;
		}
		cellFactories.add(factory);
		probabilities.add(p);
		currentProbabilityTotal += p;
		return true;
	}
	
	//Removes a factory
	public boolean removeSpaceFactory(CellFactory factory) {
		if(cellFactories.contains(factory)) {
			int index = cellFactories.indexOf(factory);
			cellFactories.remove(index);
			double removedProb = probabilities.remove(index);
			currentProbabilityTotal -= removedProb;
			return true;
		}
		return false;
	}
	
	public void setRNG(Random rng) {
		this.rng = rng;
	}
	
	public Random getRNG() {
		return rng;
	}
	//Chooses a random one of the contained factories
	private CellFactory selectRandomSpaceFactory() {
		double randVal = rng.nextDouble(), total = 0.0;
		for(int i = 0; i < probabilities.size(); i++) {
			total += probabilities.get(i);
			if(total > randVal) {
				return cellFactories.get(i);
			}
		}
		return cellFactories.get(cellFactories.size()-1);
	}

	@Override
	public Cell createCell() {
		int precision = 10000;
		if(Math.round(currentProbabilityTotal*10000) != precision) {
			System.out.println(currentProbabilityTotal);
			return null; //Won't work unless the total probability is 1
		}
		CellFactory factory = selectRandomSpaceFactory();
		return factory.createCell();
	}
	

}
