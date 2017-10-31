package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "yachtClub")
public class YachtClub {
	@XmlElement(name = "memberList")
	private List<Member> memberList;

	public YachtClub() {
		memberList = new ArrayList<Member>();
	}

	public void addMember(Member Member) {
		memberList.add(Member);
	}

	public void removeMember(int ID) {
		Member m = getMemberById(ID);
		if (m != null)
			memberList.remove(getMemberById(ID));

	}

	public void editMember(int oldMemberId, Member newMember) {
		Member oldMember = getMemberById(oldMemberId);
		if (oldMember != null)
			oldMember.copyOf(newMember);
	}

	public void removeAllMembers() {
		this.memberList.removeAll(memberList);
	}

	public void addAllMembers(List<Member> MemberList) {
		this.memberList.addAll(MemberList);
	}

	public Member getMemberByIndex(int index) {
		if (index < getNumberOfMembers() && index > -1)
			return memberList.get(index);
		return null;
	}

	public Member getMemberById(int ID) {
		for (Member Member : memberList)
			if (Member.getId() == ID)
				return Member;
		return null;
	}

	public int getNumberOfMembers() {
		return memberList.size();
	}

}
