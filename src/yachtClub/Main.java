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

public class Main extends Application {
	public static YachtClub yachtClub = new YachtClub();
    public static void main(String[] args){
    	defaultProject();
    	YachtClubDAO ycDAO= new YachtClubDAO();
    	File file = new File("src/yachtClub.xml");
    	if (!file.exists()) ycDAO.jaxbObjectToXML(yachtClub);
    	yachtClub=ycDAO.jaxbXMLToObject();
    	if (yachtClub.getMemberList().get(0).getId()!=1 || !yachtClub.getMember(1).getUserName().equals("alaa")) {yachtClub=new YachtClub();model.User.IDstatic=1;model.Boat.IDstatic=1;defaultProject(); ycDAO.jaxbObjectToXML(yachtClub);yachtClub=ycDAO.jaxbXMLToObject();}
        launch(args);
    }
    
    Login login = new Login();
    public void start(Stage primaryStage) throws MalformedURLException {
    	
    	login.start(primaryStage);
    }
    
    private static void defaultProject() {
    	
    	yachtClub.addMember(new Member("Alaa", "801201-5555", "alaa","alaa"));
    	yachtClub.addMember(new Member("Amr","901102-6666", "amr","amr"));
    	yachtClub.getMember(1).addBoat(new Boat(Type.Sailboat, 5,1));
    	yachtClub.getMember(1).addBoat(new Boat(Type.kayak, 4,1));
    	yachtClub.getMember(2).addBoat(new Boat(Type.Motorsailer, 3,2));
    	yachtClub.setSecretary(new Secretary("Secretary", "850805-1111","secretary","secretary"));
    }

}

   

