package model;

import java.time.Month;
import java.util.List;

public class MemberFilter {
	private List<Member> memberList;
	private Boolean searchByName;
	private String name;
	Boolean searchByBirthMonth;
	int month;
	
	public MemberFilter(List<Member> MemberList, Boolean SearchByName,String Name, Boolean SearchByBirthMonth, int Month) {
		memberList = MemberList;
		searchByName = SearchByName;
		searchByBirthMonth = SearchByBirthMonth;
		name = Name;
		month = Month;

	}
	
	public List<Member> getFilteredMemberList(){
		if (searchByName) {
			for (int i=0; i<memberList.size(); i++) {
				if (!memberList.get(i).getName().toLowerCase().contains(name.toLowerCase()))
					memberList.remove(i--);
			}
		}
		
		if (searchByBirthMonth) {
			for (int i=0; i<memberList.size(); i++) {
				if (!memberList.get(i).getBornDate().getMonth().equals(Month.of(month)))
					memberList.remove(i--);
			}
		}
		
		return memberList;
	}
}
