import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class LeaderElection extends LC0_Algorithm {
	
	@Override
	public String getDescription() {
	return "Election Leader 1426";
	}
	
	@Override
	protected void beforeStart() {
		setLocalProperty("label", vertex.getLabel());
		setLocalProperty("counter", vertex.getDegree());
		
		putProperty("Affichage: ", getLocalProperty("counter"), SimulationConstants.PropertyStatus.DISPLAYED);
	}

	@Override
	protected void onStarCenter() {

		// N(1)---N(x) ---> F(0)---N(x-1)
		if ( getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("N")) {
			if((int)getLocalProperty("counter") == 1 && (int)getNeighborProperty("counter") > 1) {
				setLocalProperty("label", "F");
				setNeighborProperty("counter", (int)getNeighborProperty("counter")-1);
				setLocalProperty("counter", 0);
			}
			// N(1)---N(1) ---> E(0)---F(0)
			else if((int)getLocalProperty("counter") == 1 && (int)getNeighborProperty("counter") == 1) {
				setLocalProperty("label", "E");
				setNeighborProperty("label", "F");
				setLocalProperty("counter", 0);
				setNeighborProperty("counter", 0);
			}	
			
		}
		putProperty("Affichage: ", getLocalProperty("counter"), SimulationConstants.PropertyStatus.DISPLAYED);
	}
	
	@Override
	public Object clone() {
		return new LeaderElection();
	}
}
