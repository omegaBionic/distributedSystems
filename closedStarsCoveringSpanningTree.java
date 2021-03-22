import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class closedStarsCoveringSpanningTree extends LC2_Algorithm {
	
	private static final long serialVersionUID = 0xFF99CC;

	@Override
	public String getDescription() {
		return " Spanning Tree Algorithm Using Closed Star. \n"
			+ "Rule n°2 : If star center is A, all its neighbors becomes A "
			+ "and for all neighboors x, (center)----(x) ==> (center)====(x)  \n";
	}
	
	@Override
	protected void beforeStart(){
		setLocalProperty("label", vertex.getLabel());
	}
	
	@Override
	protected void onStarCenter(){

		// Rule 
		if (getLocalProperty("label").equals("A") ) {
			int count = 0;
			for (int i = 0; i < getActiveDoors().size(); i++) {
				int port = getActiveDoors().get(i);
				if (getNeighborProperty(port, "label").equals("N")) {
					setNeighborProperty(port, "label", "A");
					setDoorState(new MarkedState(true), port);
					count++;
				}
			}
			
			if (count == 0) {
				localTermination();
			}
		}
	}
	
	@Override
	public Object clone(){
		return new closedStarsCoveringSpanningTree();
	}
}
