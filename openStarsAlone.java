import visidia.simulation.SimulationConstants;
import visidia.simulation.process.edgestate.MarkedState;
import visidia.simulation.process.algorithm.LC1_Algorithm;

public class OpenStarsAlone extends LC1_Algorithm {
	
	@Override
	public String getDescription() {
	return "Spanning Tree 1526";
	}
	
	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
		setLocalProperty("counter", vertex.getDegree());
		
		//putProperty("Affichage: ", getLocalProperty("counter"), SimulationConstants.PropertyStatus.DISPLAYED);
	}

	@Override
	protected void onStarCenter() {

		// Centre à l'état N et a au moins à l'état A => marquer à l'arête
		for(int i=0; i < getActiveDoors().size(); i++) {
			if (getLocalProperty("label").equals("N") && getNeighborProperty(i, "label").equals("A")) {
				setLocalProperty("label", "A");
				setDoorState(new MarkedState(true), i);
				break;
			}
		}
		
		//putProperty("Affichage: ", getLocalProperty("counter"), SimulationConstants.PropertyStatus.DISPLAYED);
	}
	
	@Override
	public Object clone() {
		return new OpenStarsAlone();
	}
	
}
