package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class BoatList {
	
	List <Boat> boats = new ArrayList <Boat>();
	
	public BoatList () {
		boats = new ArrayList <Boat>();
	}
	
	public void addBoat (Boat boat) {
		boats.add(boat);
	}
	
	public void removeBoat (Boat boat) {
		boats.remove(boat);
	}
	
	public void editBoat (Boat oldBoat, Boat newBoat) {
		oldBoat.copyOf(newBoat);
	}
	
	
	public Boat getBoat (int ID) {
		for (Boat Boat : boats)
			if (Boat.getId()==ID) return Boat;
		return null;
	}
	
	@XmlElement(name = "boat")
	public List<Boat> getBoats () {
		return boats;
	}
	
	public void setBoats (List<Boat> BoatList) {
		boats = BoatList;
	}
	
	public int getNumberOfBoats () {
		return boats.size();
	}
	
}

