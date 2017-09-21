package model;

public class Member extends User {
	private BoatList boatList = new BoatList();
	
	
	
	
	public BoatList getBoatList() {
		return boatList;
	}




	public void setBoatList(BoatList boatList) {
		this.boatList = boatList;
	}




	public void copyOf (Member Member) {
		setName (Member.getName());
		setPersonalNumber(Member.getPersonalNumber());
		setUserName(Member.getUserName());
		setPassword (Member.getPassword());
	}
}
