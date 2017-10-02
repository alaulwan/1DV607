package view;
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import yachtClub.Program;

public class Login extends Application {
ViewList viewList = new ViewList();
 String user = "JavaFX2";
 String pw = "password";
 String checkUser, checkPw;
 
    /*public static void main(String[] args) {
        launch(args);
    }*/
     
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
    public void start(Stage primaryStage) throws MalformedURLException {
        primaryStage.setTitle("YachClub Login");
        
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));
        
        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));
        
        //Adding GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
       //Implementing Nodes for GridPane
        Label lblUserName = new Label("Username");
        final TextField userName = new TextField("secretary");
        Label lblPassword = new Label("Password");
        final PasswordField password = new PasswordField();
        password.setText("secretary");
        Button btnLogin = new Button("Sign in  ");
        Button guest = new Button("Enter as guest");
        Button signUp = new Button("Sign Up");
        final Label lblMessage = new Label();
        
        //Adding Nodes to GridPane layout
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(userName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(password, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(guest, 2, 0);
        gridPane.add(signUp, 2, 2);
        gridPane.add(lblMessage, 1, 2);
        
                
        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);
        
        //DropShadow effect 
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        
        //Adding text and DropShadow effect to it
        Text text = new Text("Yacht Club Login");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);
        
        //Adding text to HBox
        hb.getChildren().add(text);
                          
        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        signUp.setId("btnLogin");
        guest.setId("btnLogin");
        text.setId("text");
        
        //Action for btnLogin
        btnLogin.setOnAction(new EventHandler() {
         @Override
		public void handle(Event arg0) {
        	 if (Program.yachtClub.logIn(userName.getText(), password.getText())) {
    				 primaryStage.setTitle("YachClub");
    				 primaryStage.getScene().setRoot(viewList.BorderPane());
        	 }
    			 	 
		}
         });
        signUp.setOnAction(new EventHandler() {
            @Override
    		public void handle(Event arg0) {
            	addMember();
    		}
             });
        //Action for view list
        guest.setOnAction(new EventHandler() {
         @Override
		public void handle(Event arg0) {
        	 Program.yachtClub.setLogedInUser(null);
        	 primaryStage.setTitle("YachClub (Guest)");
        	 primaryStage.getScene().setRoot(viewList.BorderPane());
		}
         });
        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);  
     Scene scene = new Scene(bp,800,800);
     // getStylesheets from .bin folder
     File file= new File ("src/login.css");
     scene.getStylesheets().add(file.toURL().toExternalForm());
     //scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
     primaryStage.setScene(scene);
     
       /*primaryStage.titleProperty().bind(
                 scene.widthProperty().asString().
                 concat(" : ").
                 concat(scene.heightProperty().asString()));*/
     primaryStage.show();
    }

	protected void addMember() {
		MemberHandler ading = new MemberHandler();
    	ading.createPopup(null, true);
		
	}
}