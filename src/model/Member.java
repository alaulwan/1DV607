package model;

import javafx.beans.property.SimpleStringProperty;

public class Member extends User {
	private BoatList boatList = new BoatList();
	  private final SimpleStringProperty MemberName;
      private final SimpleStringProperty MemberID;
      private final SimpleStringProperty NumberOfBoats;

      public Member(String MemberName, String MemberID, String NumberOfBoats) {
          this.MemberName = new SimpleStringProperty(MemberName);
          this.MemberID = new SimpleStringProperty(MemberID);
          this.NumberOfBoats = new SimpleStringProperty(NumberOfBoats);
      }

      public String getMemberName() {
          return MemberName.get();
      }

      public void setName(String Name) {
      	MemberName.set(Name);
      }

      public String getMemberID() {
          return MemberID.get();
      }

      public void setID(String id) {
      	MemberID.set(id);
      }

      public String getNumberOfBoats() {
          return NumberOfBoats.get();
      }

      public void setNumberOfBoats(String BNum) {
      	NumberOfBoats.set(BNum);
      }
	
	
	
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
