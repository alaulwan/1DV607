package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//import javafx.beans.property.SimpleStringProperty;


public class Member extends User {

	@XmlElement(name = "boats")
	private BoatList boats = new BoatList();
	  //private final SimpleStringProperty MemberName;
      //private final SimpleStringProperty MemberID;
      //private final SimpleStringProperty NumberOfBoats;

	
    public Member() {
    	super();
  	  
    }
	
      public Member(String MemberName, String MemberID) {
          //this.MemberName = new SimpleStringProperty(MemberName);
          //this.MemberID = new SimpleStringProperty(MemberID);
          //this.NumberOfBoats = new SimpleStringProperty(NumberOfBoats);
    	  super(MemberName, MemberID);
    	  
      }

      /*public String getMemberName() {
          return MemberName.get();
      }*/

      /*public void setName(String Name) {
      	MemberName.set(Name);
      }*/

      /*public String getMemberID() {
          return MemberID.get();
      }*/

      /*public void setID(String id) {
      	MemberID.set(id);
      }*/

      /*public String getNumberOfBoats() {
          return NumberOfBoats.get();
      }*/

      /*public void setNumberOfBoats(String BNum) {
      	NumberOfBoats.set(BNum);
      }*/
	
    public Member(String Name, String PersonalNumber ,String UserName, String Password ) {
  		super(Name,PersonalNumber, UserName, Password);
  	}
	
	public BoatList getBoats() {
		return boats;
	}

	public int getNumberOfBoats() {
		if (boats==null) return 0;
		return boats.getNumberOfBoats();
	}
	
	


	public void setBoatList(BoatList boatList) {
		this.boats = boatList;
	}




	public void copyOf (Member Member) {
		setName (Member.getName());
		setPersonalNumber(Member.getPersonalNumber());
		setUserName(Member.getUserName());
		setPassword (Member.getPassword());
	}
}
