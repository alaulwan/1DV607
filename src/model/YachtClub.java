package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "yachtClub")
public class YachtClub {
	private Secretary secretary;
	private MemberList memberList ;
	//private BoatList boatList ;
	public YachtClub() {
		secretary = new Secretary(null, null, null);
		memberList = new MemberList();
		//boatList = new BoatList();
	}
	
	public MemberList getMemberList() {
		return memberList;
	}

	public void setMemberList(MemberList memberList) {
		this.memberList = memberList;
	}

	/*public BoatList getBoatList() {
		return boatList;
	}

	public void setBoatList(BoatList boatList) {
		this.boatList = boatList;
	}*/

	public Secretary getSecretary () {
		return secretary ;
		
	}
	
	public void setSecretary (Secretary Secretary) {
		secretary = Secretary;
		
	}
	
	public void addMember(Member Member) {
		memberList.addMember(Member);
	}
	
	/*public void addBoat(Boat Boat) {
		boatList.addBoat(Boat);
	}*/
	
}
