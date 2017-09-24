package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddMemberPopup {
	
	@SuppressWarnings("rawtypes")
	public void createPopup() {
		Pane root=new Pane();
		Stage stage=new Stage();
		stage.setTitle("Add Member");
		Button done = new Button("Done!");
		done.setId("btnLogin");
		
		TextField name = new TextField();
		Text nam = new Text("Name");
		TextField PersonalNumber = new TextField();
		Text pn = new Text("PersonalNumber");
		Text bl = new Text("Boat Length");
		Text bt = new Text("Boat Type");
		TextField BoatLength = new TextField();
		 ChoiceBox cb = new ChoiceBox();
		 cb.getItems().addAll("SailBoat", "Motor", "Other");
		VBox container = new VBox(5);
		container.getChildren().addAll(nam,name,pn,PersonalNumber,bl,BoatLength,bt,cb,done);
		root.getChildren().add(container);
		Scene scene = new Scene(root,250,250);
		stage.setScene(scene);
		root.setId("bp");
		 scene.getStylesheets().add(getClass().getClassLoader().
				 getResource("login.css").toExternalForm());
	     
		stage.show();
	    
	}
}
