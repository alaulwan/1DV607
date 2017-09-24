package yachtClub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Boat;
import model.Boat.Type;
import model.Member;
import model.Secretary;
import model.YachtClub;
import model.YachtClubDAO;
import view.Login;

public class Main extends Application {
	public static YachtClub yachtClub = new YachtClub();
    public static void main(String[] args){
    	yachtClub.addMember(new Member("Alaa", "555555-5555", "alaaUSER","alaaPASS"));
    	yachtClub.addMember(new Member("Amr","666666-6666", "amrUSER","amrPASS"));
    	yachtClub.getMemberList().getMember(1).getBoats().addBoat(new Boat(Type.Sailboat, 5,1));
    	yachtClub.getMemberList().getMember(1).getBoats().addBoat(new Boat(Type.kayak, 4,1));
    	yachtClub.getMemberList().getMember(2).getBoats().addBoat(new Boat(Type.Motorsailer, 3,2));
    	yachtClub.setSecretary(new Secretary("iugh", "111111-1111","ij","gg"));
    	YachtClubDAO ycDAO= new YachtClubDAO();
    	File file = new File("src/yachtClub.xml");
    	if (!file.exists()) ycDAO.jaxbObjectToXML();
    	yachtClub=ycDAO.jaxbXMLToObject();
        launch(args);
    }
    
    Login login = new Login();
    public void start(Stage primaryStage) {
    	login.start(primaryStage);
    }

}

   

