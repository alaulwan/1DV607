package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "yachtClub")
public class YachtClub {
	private List<Member> memberList;
	//private BoatList boatList ;
	public YachtClub() {
		memberList = new ArrayList<Member> ();
		//boatList = new BoatList();
	}
	
	
	public List<Member>  getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member>  MemberList) {
		this.memberList = MemberList;
	}



	
	public void addMember(Member Member) {
		memberList.add(Member);
	}

	public void removeMember(Member member) {
		memberList.remove(member);
		
	}
	

	public void editMember (Member oldMember, Member newMember) {
		oldMember.copyOf(newMember);
	}
	
	public Member getMember (int ID) {
		for (Member Member : memberList)
			if (Member.getId()==ID) return Member;
		return null;
	}

	
	public int getNumberOfMembers () {
		return memberList.size();
	}
	
	


	
	
}
