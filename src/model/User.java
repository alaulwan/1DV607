package model;

import java.time.LocalDate;

public class User {
	public static int IDstatic = 0;
	private int id ;
	private String name = new String();
	private String personalNumber = new String();
	private String userName = new String();
	private String password = new String();
	private LocalDate birthDate;
	
	public User() {
		id=IDstatic++;
		this.setName("gu");
	}
	
	public User(String MemberName, String MemberID) {
		id=IDstatic++;
		this.setName(MemberName);
		this.setId(Integer.valueOf(MemberID));
	}
	
	public User(String Name, String PersonalNumber, String UserName, String Password) {
		id=IDstatic++;
		this.setName(Name);
		this.setUserName(UserName);
		this.setPassword(Password);
		this.setPersonalNumber(PersonalNumber);
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(String PersonalNumber) {
		this.personalNumber = PersonalNumber;
		setBornDate(personalNumber);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBornDate(String PersonalNumber) {
		String year = PersonalNumber.substring(0,2);
		String month = PersonalNumber.substring(2,4);
		String day = PersonalNumber.substring(4,6);
		int Year = Integer.valueOf(year);
		int Month = Integer.valueOf(month);
		int Day = Integer.valueOf(day);
		birthDate = LocalDate.of(Year, Month, Day);
	}
	
	

}
