package view;

import java.time.DateTimeException;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Member;
import yachtClub.Program;

// This class verifies that user's input is correct to create new member or boat
public class InputVerifier {
	public boolean isCorrectMember(String Name, String PersonalNumber, String UserName, String Password,
			Member Member) {

		return (isCorrectMemberName(Name) && isCorrectMembePersonalNumber(PersonalNumber, Member)
				&& isCorrectMemberUserName(UserName, Member) && isCorrectMemberPassword(Password));
	}

	public boolean isCorrectBoat(String Length) {
		try {
			Double.parseDouble(Length);
		} catch (NumberFormatException e) {
			alertWindow(AlertType.ERROR, "ERROR!", "Cannot add boat!", "Length should be a float number");
			return false;
		}
		return true;

	}

	private boolean isCorrectMemberPassword(String Password) {
		if (Password.replaceAll("\\s+", "").isEmpty()) {
			alertWindow(AlertType.ERROR, "ERROR!", "Cannot add member!", "Member should have a password");
			return false;
		}
		return true;
	}

	private boolean isCorrectMemberUserName(String UserName, Member Member) {
		if (UserName.replaceAll("\\s+", "").isEmpty()) {
			alertWindow(AlertType.ERROR, "ERROR!", "Cannot add member!", "Member should have User Name");
			return false;
		}
		if (Member == null) {
			for (Member m : Program.yachtClub.getMemberList()) {
				if (m.getUserName().equals(UserName)) {
					alertWindow(AlertType.ERROR, "ERROR!", "Cannot add member!", "UserName is already exists");
					return false;
				}
			}
		}

		return true;
	}

	private boolean isCorrectMembePersonalNumber(String PersonalNumber, Member Member) {
		if (PersonalNumber.length() < 6 || !isDate(PersonalNumber.substring(0, 6))) {
			alertWindow(AlertType.ERROR, "ERROR!", "Cannot add member!",
					"First six charecters in PERSONAL NUMBER should be the birth date");
			return false;
		}
		if (Member == null) {
			for (Member m : Program.yachtClub.getMemberList()) {
				if (m.getPersonalNumber().equals(PersonalNumber)) {
					alertWindow(AlertType.ERROR, "ERROR!", "Cannot add member!", "Personal Number is already exists");
					return false;
				}
			}
		}

		return true;
	}

	private boolean isCorrectMemberName(String name) {
		if (name.replaceAll("\\s+", "").isEmpty()) {
			alertWindow(AlertType.ERROR, "ERROR!", "Cannot add member!", "Member should have name");
			return false;
		}
		return true;
	}

	public static boolean isInteger(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (!Character.isDigit(text.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean isDate(String text) {
		if (text.length() != 6 || !isInteger(text))
			return false;
		String year = text.substring(0, 2);
		String month = text.substring(2, 4);
		String day = text.substring(4, 6);
		int Year = Integer.valueOf(year);
		int Month = Integer.valueOf(month);
		int Day = Integer.valueOf(day);

		if (Month > 12 || Month == 0 || Day > 31 || Day == 0) {
			return false;
		}

		try {
			LocalDate.of(Year, Month, Day);
		} catch (DateTimeException e) {
			return false;
		}
		return true;
	}

	/**
	 * Warning popUp
	 * 
	 * @param type
	 *            of alert
	 * @param Title
	 * @param headText
	 * @param contentText
	 */
	public void alertWindow(AlertType type, String Title, String headText, String contentText) {

		Alert alert = new Alert(type);
		alert.setTitle(Title);
		alert.setHeaderText(headText);
		alert.setContentText(contentText);
		alert.showAndWait();

	}
}
