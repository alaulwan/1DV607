package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import yachtClub.Program;

@XmlRootElement(name = "yachtClub")
public class YachtClub {
	private User logedInUser;
	private Secretary secretary;
	private List<Member> memberList;

	// private BoatList boatList ;
	public YachtClub() {
		setLogedInUser(null);
		secretary = new Secretary();
		memberList = new ArrayList<Member>();
		// boatList = new BoatList();
	}

	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> MemberList) {
		this.memberList = MemberList;
	}

	public Secretary getSecretary() {
		return secretary;

	}

	public void setSecretary(Secretary Secretary) {
		secretary = Secretary;

	}

	public void addMember(Member Member) {
		memberList.add(Member);
	}

	public void removeMember(Member member) {
		memberList.remove(member);

	}

	public void editMember(Member oldMember, Member newMember) {
		oldMember.copyOf(newMember);
	}

	public Member getMember(int ID) {
		for (Member Member : memberList)
			if (Member.getId() == ID)
				return Member;
		return null;
	}

	public int getNumberOfMembers() {
		return memberList.size();
	}

	public boolean logIn(String UserName, String Password) {
		if (secretary.getUserName().equals(UserName)) {
			if (secretary.getPassword().equals(Password)) {
				setLogedInUser(secretary);
				return true;
			}
		}
		for (model.Member m : this.getMemberList()) {
			if (m.getUserName().equals(UserName)) {
				if (m.getPassword().equals(Password)) {
					setLogedInUser(m);
					return true;
				}
			}
		}
		return false;
	}

	public User getLogedInUser() {
		return logedInUser;
	}

	public void setLogedInUser(User logedInUser) {
		this.logedInUser = logedInUser;
	}

}
