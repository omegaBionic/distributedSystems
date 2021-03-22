import visidia.simulation.SimulationConstants;
import visidia.simulation.process.edgestate.MarkedState;
import visidia.simulation.process.algorithm.LC2_Algorithm;

public class SpinningTreeClosed extends LC2_Algorithm {


	@Override
	public String getDescription() {
		return "Election d'un leader dans un arbre 1715";
	}

	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
		setLocalProperty("counter", vertex.getDegree());
		

		//putProperty("Affichage: ", getLocalProperty("counter"), SimulationConstants.PropertyStatus.DISPLAYED);
	}

	@Override
	protected void onStarCenter() {

		if(getLocalProperty("label").equals("N")) {
			
			for(int i = 0; i < getActiveDoors().size(); i++) {
				int port = getActiveDoors().get(i);
				if(getNeighborProperty(port, "label").equals("N") && (int)getNeighborProperty(port, "counter") == 1) {
					setNeighborProperty(port,"label", "F");
					int counter = (int)getLocalProperty("counter");
					setLocalProperty("counter", counter-1);
				}
			
			}
			if((int)getLocalProperty("counter") == 0) {
				setLocalProperty("label", "E");
			}
		}
		
		//putProperty("Affichage: ", getLocalProperty("counter"), SimulationConstants.PropertyStatus.DISPLAYED);
	}

	@Override
	public Object clone() {
		return new SpinningTreeClosed();
	}
}
