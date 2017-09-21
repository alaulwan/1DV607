package model;

import java.util.ArrayList;
import java.util.List;

public class BoatList {
	List <Boat> boatList = new ArrayList <Boat>();
	
	public BoatList () {
		boatList = new ArrayList <Boat>();
	}
	
	public void addBoat (Boat boat) {
		boatList.add(boat);
	}
	
	public void removeBoat (Boat boat) {
		boatList.remove(boat);
	}
	
	public void editBoat (Boat oldBoat, Boat newBoat) {
		oldBoat.copyOf(newBoat);
	}
	
	public List getBoats () {
		return boatList;
	}
	
	public void setBoats (List BoatList) {
		boatList = BoatList;
	}
	
	public int getNumberOfBoats () {
		return boatList.size();
	}
	
}

