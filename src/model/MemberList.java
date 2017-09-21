package model;

import java.util.ArrayList;
import java.util.List;

public class MemberList {
	List <Member> memberList = new ArrayList <Member>();
	
	public MemberList () {
		memberList = new ArrayList <Member>();
	}
	
	public void addMember (Member member) {
		memberList.add(member);
	}
	
	public void removeMember (Member member) {
		memberList.remove(member);
	}
	
	public void editMember (Member oldMember, Member newMember) {
		oldMember.copyOf(newMember);
	}
	
	public List getMembers () {
		return memberList;
	}
	
	public void setMembers (List MemberList) {
		memberList = MemberList;
	}
	
	public int getNumberOfMembers () {
		return memberList.size();
	}
}
