package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class Member extends User {
	@XmlElement(name = "boatList")
	private List<Boat> boatList;

	public Member() {
		super();
		boatList = new ArrayList<Boat>();

	}

	public Member(String MemberName, String MemberID) {
		super(MemberName, MemberID);
		boatList = new ArrayList<Boat>();

	}

	public Member(String Name, String PersonalNumber, String UserName, String Password) {
		super(Name, PersonalNumber, UserName, Password);
		boatList = new ArrayList<Boat>();
	}

	public int getNumberOfBoats() {
		if (boatList == null)
			return 0;
		return boatList.size();
	}

	public void addBoat(Boat Boat) {
		boatList.add(Boat);
	}

	public void removeBoat(int boatId) {
		Boat boat = getBoatById(boatId);
		if (boat != null)
			boatList.remove(boat);

	}

	public void editBoat(int oldBoatId, Boat newBoat) {
		Boat oldBoat = getBoatById(oldBoatId);
		if (oldBoat != null)
			oldBoat.copyOf(newBoat);
	}

	public Boat getBoatById(int ID) {
		for (Boat Boat : boatList)
			if (Boat.getId() == ID)
				return Boat;
		return null;
	}

	public Boat getBoatByIndex(int index) {
		if (index < getNumberOfBoats() && index > -1)
			return boatList.get(index);
		return null;
	}

	public void removeAllBoats() {
		this.boatList.removeAll(boatList);
	}

	public void addAllBoats(List<Boat> BoatList) {
		for (Boat b : BoatList)
			b.setOwnerId(this.getId());
		this.boatList.addAll(BoatList);
	}

	public void copyOf(Member Member) {
		setName(Member.getName());
		setPersonalNumber(Member.getPersonalNumber());
		setUserName(Member.getUserName());
		setPassword(Member.getPassword());
		boatList = new ArrayList<Boat>();
		for (int i = 0; i < Member.getNumberOfBoats(); i++) {
			this.addBoat(Member.getBoatByIndex(i));
		}

	}
}
