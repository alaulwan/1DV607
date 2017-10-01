package model;

import java.time.Month;
import java.util.List;

public class MemberFilter {
	private List<Member> memberList;
	private Boolean enableNameFilter;
	private String name;
	private Boolean enableMonthFilter;
	int month;
	
	public MemberFilter(List<Member> MemberList, Boolean SearchByName,String Name, Boolean SearchByBirthMonth, int Month) {
		memberList = MemberList;
		enableNameFilter = SearchByName;
		enableMonthFilter = SearchByBirthMonth;
		name = Name;
		month = Month;

	}
	
	public List<Member> getFilteredMemberList(){
		if (enableNameFilter) {
			for (int i=0; i<memberList.size(); i++) {
				if (!memberList.get(i).getName().toLowerCase().contains(name.toLowerCase()))
					memberList.remove(i--);
			}
		}
		
		if (enableMonthFilter) {
			for (int i=0; i<memberList.size(); i++) {
				if (!memberList.get(i).getBirthDate().getMonth().equals(Month.of(month)))
					memberList.remove(i--);
			}
		}
		
		return memberList;
	}
}
