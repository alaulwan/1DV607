package yachtClub;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Boat;
import model.Boat.Type;
import model.Member;
import model.Secretary;
import model.YachtClub;
import model.YachtClubDAO;
import view.Login;

public class Program extends Application {
	public static YachtClub yachtClub = new YachtClub();

	public static void main(String[] args) {
		YachtClubDAO ycDAO = new YachtClubDAO();
		File file = new File("yachtClub.xml");
		if (!file.exists()) { // if there is no database, create new default project and save it to new data
								// base
			defaultProject();
			ycDAO.jaxbObjectToXML(yachtClub);
		} else // else load the project from the database
			yachtClub = ycDAO.jaxbXMLToObject();
		launch(args);
	}

	Login login = new Login();

	public void start(Stage primaryStage) throws MalformedURLException {

		login.start(primaryStage);
	}

	// Method to create default project
	private static void defaultProject() {
		yachtClub.addMember(new Member("member1", "101010-5555", "aaaa", "aaaa"));
		yachtClub.addMember(new Member("member2", "111111-6666", "bbbb", "bbbb"));
		yachtClub.getMember(1).addBoat(new Boat(Type.Sailboat, 5, 1));
		yachtClub.getMember(1).addBoat(new Boat(Type.kayak, 4, 1));
		yachtClub.getMember(2).addBoat(new Boat(Type.Motorsailer, 3, 2));
		yachtClub.setSecretary(new Secretary("Secretary", "121212-1111", "secretary", "secretary"));
	}

}
