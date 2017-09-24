package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class MemberList {
	List <Member> members = new ArrayList <Member>();
	
	public MemberList () {
		members = new ArrayList <Member>();
	}
	
	public void addMember (Member member) {
		members.add(member);
	}
	
	public void removeMember (Member member) {
		members.remove(member);
	}
	
	public void editMember (Member oldMember, Member newMember) {
		oldMember.copyOf(newMember);
	}
	
	public Member getMember (int ID) {
		for (Member Member : members)
			if (Member.getId()==ID) return Member;
		return null;
	}
	

	@XmlElement(name = "member")
	public List<Member> getMembers () {
		return members;
	}
	
	public void setMembers (List<Member> MemberList) {
		members = MemberList;
	}
	
	public int getNumberOfMembers () {
		return members.size();
	}
}
