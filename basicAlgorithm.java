package algo_base;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class BasicAlgorithm extends LC0_Algorithm{
	
	private String etatsVoisins[];
	private int fatherDoor;
	
    @Override
    public String getDescription() {
        return "Last test 1736\n-" +
                "Rule :  test";
    }

    @Override
    public void beforeStart() {
    	
    	// New part
    	etatsVoisins = new String[vertex.getDegree()];
    	for(int i = 0; i < vertex.getDegree(); i++) {
    		etatsVoisins[i] = "N";
    	}
    	setLocalProperty("compteur", 1);
    	
    	
    	// New part
        setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
    	// New part
    	etatsVoisins[neighborDoor] = getNeighborProperty("label").toString();
    	// N--A --> A--M
    	if(getLocalProperty("label").equals("N") && 
    			getNeighborProperty("label").equals("A")) {
    		setLocalProperty("label", "A");
    		setNeighborProperty("label", "M");
    		setDoorState(new MarkedState(true), neighborDoor);
    		fatherDoor = neighborDoor;
    	}
    	
    	// A--M --> F--A
    	else if(getLocalProperty("label").equals("A") &&
    			getNeighborProperty("label").equals("M") &&
    			fatherDoor == neighborDoor && 
    			!aUnVoisin()) {
    		setLocalProperty("label", "F");
    		setNeighborProperty("label", "A");
    		
    		setNeighborProperty("compteur", (int)getLocalProperty("compteur") + (int)getNeighborProperty("compteur"));
    		
    	}
    	
    	putProperty("Affichage", (int)getLocalProperty("compteur"), SimulationConstants.PropertyStatus.DISPLAYED);
    	
    	// Old part
    	/*
        if (getLocalProperty("label").equals("A") &&
            getNeighborProperty("label").equals("N")) {
            setNeighborProperty("label", "A");
            setDoorState(new MarkedState(true), neighborDoor);
        }
        */
    }

    @Override
    public Object clone(){
        return new BasicAlgorithm();
    }
    
    
    // nouvelle partie
    boolean aUnVoisin() {
    	int i = 0;
    	while(i < vertex.degree()) {
    		if(etatsVoisins[i].equals("N"))
    			return true;
    		i++;
    	}
    	return false;
    }
}
