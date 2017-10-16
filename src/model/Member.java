package model;

import java.util.ArrayList;
import java.util.List;

//import javafx.beans.property.SimpleStringProperty;

public class Member extends User {

	private List<Boat> boatList;
	// private final SimpleStringProperty MemberName;
	// private final SimpleStringProperty MemberID;
	// private final SimpleStringProperty NumberOfBoats;

	public Member() {
		super();
		boatList = new ArrayList<Boat>();

	}

	public Member(String MemberName, String MemberID) {
		// this.MemberName = new SimpleStringProperty(MemberName);
		// this.MemberID = new SimpleStringProperty(MemberID);
		// this.NumberOfBoats = new SimpleStringProperty(NumberOfBoats);
		super(MemberName, MemberID);
		boatList = new ArrayList<Boat>();

	}

	/*
	 * public String getMemberName() { return MemberName.get(); }
	 */

	/*
	 * public void setName(String Name) { MemberName.set(Name); }
	 */

	/*
	 * public String getMemberID() { return MemberID.get(); }
	 */

	/*
	 * public void setID(String id) { MemberID.set(id); }
	 */

	/*
	 * public String getNumberOfBoats() { return NumberOfBoats.get(); }
	 */

	/*
	 * public void setNumberOfBoats(String BNum) { NumberOfBoats.set(BNum); }
	 */

	public Member(String Name, String PersonalNumber, String UserName, String Password) {
		super(Name, PersonalNumber, UserName, Password);
		boatList = new ArrayList<Boat>();
	}

	public List<Boat> getBoatList() {
		return boatList;
	}

	public int getNumberOfBoats() {
		if (boatList == null)
			return 0;
		return boatList.size();
	}

	public void addBoat(Boat Boat) {
		boatList.add(Boat);
	}

	public void removeBoat(Boat Boat) {
		boatList.remove(Boat);
	}

	public void editBoat(Boat oldBoat, Boat newBoat) {
		oldBoat.copyOf(newBoat);
	}

	public Boat getBoat(int ID) {
		for (Boat Boat : boatList)
			if (Boat.getId() == ID)
				return Boat;
		return null;
	}

	public void setBoatList(List<Boat> BoatList) {
		this.boatList = BoatList;
		for (Boat b : boatList)
			b.setOwnerId(this.getId());
	}

	public void copyOf(Member Member) {
		setName(Member.getName());
		setPersonalNumber(Member.getPersonalNumber());
		setUserName(Member.getUserName());
		setPassword(Member.getPassword());
		setBoatList(Member.getBoatList());

	}
}
